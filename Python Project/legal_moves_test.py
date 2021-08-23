from legal_move import LegalMove


def test_constructor():
    """Test constructor"""

    lm = LegalMove(4)

    assert lm.moves == []
    assert lm.GRID_CT == 4

    # test with legal move example provided in hw11
    items = [[0, "b", 0, 0], [0, "b", "b", 0], [0, "b", "w", 0], [0, 0, 0, 0]]
    lm.find_all_moves("w", items)
    assert len(lm.moves) == 3
    # cell (1, 0) is a legal move for white tile
    assert lm.moves[0][0] == 0
    assert lm.moves[0][1] == 0

    # cell (1, 2) is a legal move for white tile
    assert lm.moves[1][0] == 0
    assert lm.moves[1][1] == 2

    # cell (3, 0) is a legal move for white tile
    assert lm.moves[2][0] == 2
    assert lm.moves[2][1] == 0

    assert lm.moves == (
        [[0, 0, [[1, 1], [2, 2]], 'up left'],
            [0, 2, [[1, 2], [2, 2]], 'up'],
            [2, 0, [[2, 1], [2, 2]], 'left']]
    )


def test_check_surrounding():
    """Test check surrounding method"""
    lm = LegalMove(4)
    items = [[0, "b", 0, 0], [0, "b", "b", 0], [0, "b", "w", 0], [0, 0, 0, 0]]
    lm.check_surrounding(items, 1, 1, "w")
    assert lm.moves == [[0, 0, [[1, 1], [2, 2]], 'up left']]

    lm = LegalMove(4)
    items = [[0, "b", 0, 0], [0, "b", "b", 0], [0, "b", "w", 0], [0, 0, 0, 0]]
    lm.check_surrounding(items, 1, 2, "w")
    assert lm.moves == [[0, 2, [[1, 2], [2, 2]], 'up']]

    lm = LegalMove(4)
    items = [[0, "b", 0, 0], [0, "b", "b", 0], [0, "b", "w", 0], [0, 0, 0, 0]]
    lm.check_surrounding(items, 2, 1, "w")
    assert lm.moves == [[2, 0, [[2, 1], [2, 2]], 'left']]


def test_check_cell_up():
    """Test check cell up method"""
    lm = LegalMove(4)
    items = [[0, "b", 0, 0], [0, "b", "b", 0], [0, "b", "w", 0], [0, 0, 0, 0]]
    assert lm.check_cell_up(items, 1, 1, "w") is None


def test_check_cell_down():
    """Test check cell down method"""
    lm = LegalMove(4)
    items = [[0, "b", 0, 0], [0, "b", "b", 0], [0, "b", "w", 0], [0, 0, 0, 0]]
    assert lm.check_cell_down(items, 1, 1, "w") is None


def test_check_cell_left():
    """Test check cell left method"""
    lm = LegalMove(4)
    items = [[0, "b", 0, 0], [0, "b", "b", 0], [0, "b", "w", 0], [0, 0, 0, 0]]
    assert lm.check_cell_left(items, 1, 1, "w") is None


def test_check_cell_right():
    """Test check cell right method"""
    lm = LegalMove(4)
    items = [[0, "b", 0, 0], [0, "b", "b", 0], [0, "b", "w", 0], [0, 0, 0, 0]]
    assert lm.check_cell_right(items, 1, 1, "w") is None


def test_check_cell_up_left():
    """Test check cell upper left method"""
    lm = LegalMove(4)
    items = [[0, "b", 0, 0], [0, "b", "b", 0], [0, "b", "w", 0], [0, 0, 0, 0]]
    assert lm.check_cell_up_left(items, 1, 1, "w") == (
        [0, 0, [[1, 1], [2, 2]], 'up left']
    )


def test_check_cell_up_right():
    """Test check cell up right method"""
    lm = LegalMove(4)
    items = [[0, "b", 0, 0], [0, "b", "b", 0], [0, "b", "w", 0], [0, 0, 0, 0]]
    assert lm.check_cell_up_right(items, 1, 1, "w") is None


def test_check_cell_lower_left():
    """Test check cell lower left method"""
    lm = LegalMove(4)
    items = [[0, "b", 0, 0], [0, "b", "b", 0], [0, "b", "w", 0], [0, 0, 0, 0]]
    assert lm.check_cell_lower_left(items, 1, 1, "w") is None


def test_check_cell_lower_right():
    """Test check cell lower right method"""
    lm = LegalMove(4)
    items = [[0, "b", 0, 0], [0, "b", "b", 0], [0, "b", "w", 0], [0, 0, 0, 0]]
    assert lm.check_cell_lower_right(items, 1, 1, "w") is None
