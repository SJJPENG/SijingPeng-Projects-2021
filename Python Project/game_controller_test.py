from game_controller import GameController


def test_constructor():
    """Test constructor"""
    gc = GameController(400, 400, 4, .95)

    assert gc.WIDTH == 400
    assert gc.HEIGHT == 400
    assert gc.GRID_CT == 4
    assert gc.DISK_FILL == .95
    assert gc.TWO == 2
    assert gc.player_trun is True
    assert gc.BLACK == 0
    assert gc.WHITE == 255
    assert gc.STROKE_WEIGHT == 2
    assert gc.player_message is None
    assert gc.computer_message is None
    assert gc.game_end is False

    assert gc.grid_size == gc.WIDTH//gc.GRID_CT
    assert gc.half_grid_ct == gc.GRID_CT//gc.TWO
    assert gc.half_grid == gc.grid_size//2

    assert len(gc.items) == gc.GRID_CT
    assert len(gc.items[0]) == gc.GRID_CT
    assert gc.items[0][0] == 0

    assert gc.items[gc.half_grid_ct - 1][gc.half_grid_ct - 1] == "w"
    assert gc.items[gc.half_grid_ct][gc.half_grid_ct] == "w"
    assert gc.items[gc.half_grid_ct][gc.half_grid_ct - 1] == "b"
    assert gc.items[gc.half_grid_ct - 1][gc.half_grid_ct] == "b"

    gc = GameController(400, 400, 2, .95)
    assert len(gc.items) == gc.GRID_CT
    assert len(gc.items[0]) == gc.GRID_CT
    assert gc.items == [["w", "b"], ["b", "w"]]


def test_decide_turn():
    """Test decide turn method"""
    gc = GameController(400, 400, 4, .95)
    gc.decide_turn(10, 10)

    # player is expect to have 4 legal moves off the bat
    assert len(gc.legal_moves) == 4

    gc = GameController(400, 400, 4, .95)
    assert gc.player_trun is True
    gc.decide_turn(10, 10)

    # player is expect to have 4 legal moves off the bat
    assert len(gc.legal_moves) == 4
    # since coords (10, 10) is not a legal move, it is still player's turn
    assert gc.player_trun is True

    # since coords (150, 25) is a legal move, a black tile is placed at
    # cell (0, 1) and now is the computer's turn
    gc.decide_turn(150, 25)
    assert gc.player_trun is False
    assert gc.items[0][1] == "b"

    # since now is the computer's turn, the mouse coords stays the same since
    # the player has not click anywhere on the canvas yet
    # the computer would make move when the decide turn method is run and
    # the program would switch to player's turn
    gc.decide_turn(150, 25)
    assert gc.player_trun is True

    # player has not click
    gc.decide_turn(150, 25)
    assert gc.player_trun is True


def test_pick_best_move():
    """Test pick best move method"""
    gc = GameController(400, 400, 8, .95)
    legal_move = (
        [[2, 3, [[3, 3], [4, 3]], 'up'],
         [3, 2, [[3, 3], [3, 4], [3, 5], [3, 6]], 'left'],
         [5, 4, [[3, 4], [4, 4]], 'down'],
         [4, 5, [[4, 3], [4, 4]], 'right']]
    )
    assert gc.pick_best_move(legal_move) == (3, 2)


def test_add_disk_to_board():
    """Test add disk to board method"""
    gc = GameController(400, 400, 8, .95)
    gc.add_disk_to_board(1, 1, "b")
    assert gc.items[1][1] == "b"


def test_flip():
    """Test flip method"""
    gc = GameController(400, 400, 8, .95)
    gc.legal_moves = [[3, 2, [[3, 3], [3, 4], [3, 5], [3, 6]], 'left']]
    gc.flip(2, 3, "b")
    assert gc.items[3][3] == "b"
    assert gc.items[3][4] == "b"
    assert gc.items[3][5] == "b"
    assert gc.items[3][6] == "b"


def test_update():
    """Test update method"""
    gc = GameController(400, 400, 4, .95)
    gc.items = (
        [[0, "b", 0, 0], [0, "b", "b", 0],
         [0, "b", "w", 0], [0, 0, 0, 0]]
    )
    gc.update("Jane")
    assert gc.game_end is False

    gc = GameController(400, 400, 4, .95)
    gc.items = (
        [["b", "b", "b", "b"], ["b", "w", "b", "b"],
         ["b", "w", "b", "b"], ["b", "b", "b", "b"]]
    )
    gc.update("Jane")
    assert gc.game_end is True

    gc = GameController(400, 400, 4, .95)
    gc.items = (
        [[0, "b", "b", 0], ["b", "w", "b", "b"],
         ["b", "w", "b", "b"], ["b", "b", "b", "b"]]
    )
    gc.update("Jane")
    assert gc.game_end is False

    gc = GameController(400, 400, 4, .95)
    gc.items = (
        [[0, "b", "b", 0], ["b", "w", "b", "b"],
         ["b", "w", "b", "b"], ["b", "b", "b", "b"]]
    )
    gc.player_message = "some message"
    gc.computer_message = "some message"
    gc.update("Jane")
    assert gc.game_end is True
