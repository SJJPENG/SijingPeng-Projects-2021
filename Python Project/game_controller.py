from legal_move import LegalMove
import os


class GameController:
    """Maintains the state of the game."""
    def __init__(self, WIDTH, HEIGHT, GRID_CT, DISK_FILL):
        """
        input: int canvas width, int canvas height, int grid count,
        float float disk fill %

        set up constant variables and call disks class.
        """
        self.WIDTH = WIDTH
        self.HEIGHT = HEIGHT
        self.GRID_CT = GRID_CT
        self.DISK_FILL = DISK_FILL
        self.TWO = 2
        self.player_trun = True
        self.BLACK = 0
        self.WHITE = 255
        self.STROKE_WEIGHT = 2
        self.player_message = None
        self.computer_message = None
        self.game_end = False

        self.grid_size = self.WIDTH//self.GRID_CT
        self.half_grid_ct = self.GRID_CT//self.TWO
        self.half_grid = self.grid_size//2

        self.items = [[0]*GRID_CT for i in range(GRID_CT)]

        # place the first 4 disks
        self.items[self.half_grid_ct - 1][self.half_grid_ct - 1] = "w"
        self.items[self.half_grid_ct][self.half_grid_ct] = "w"
        self.items[self.half_grid_ct][self.half_grid_ct - 1] = "b"
        self.items[self.half_grid_ct - 1][self.half_grid_ct] = "b"

        self.lm = LegalMove(self.GRID_CT)
        self.legal_moves = self.lm.find_all_moves("b", self.items)

    def decide_turn(self, x_coord, y_coord):
        """
        input: int x coord, int y coord

        calc the cell index based on x, y cord, based on the legal moves
        and player turn information, make the move and switch side
        """
        # calculate the index coords of the mouse pressed coordinate
        column = int(x_coord//(self.WIDTH/self.GRID_CT))
        row = int(y_coord//(self.WIDTH/self.GRID_CT))

        if self.player_trun:
            print("Player's turn")
            # if player has no legal move, switch to computer turn
            # and calc computer legal moves
            if len(self.legal_moves) == 0:
                self.player_message = "Player has no legal move."
                print(self.player_message)
                self.player_trun = False
                self.legal_moves = (
                            self.lm.find_all_moves("w", self.items)
                        )
                return
            # if player clicked an empty cell
            if self.items[row][column] == 0:
                # if the empty cell is a legal move
                for move in self.legal_moves:
                    if (
                        column == move[1] and row == move[0]
                    ):
                        # draw the tile, switch to computer turn
                        # and calc computer legal moves
                        self.player_make_move(column, row)
                        self.player_trun = False
                        self.legal_moves = (
                            self.lm.find_all_moves("w", self.items)
                        )
                        return

        if not self.player_trun:
            print("Computer's turn")
            # if computer has no legal move, switch to player turn
            # and calc player legal moves
            if len(self.legal_moves) == 0:
                self.computer_message = "Computer has no legal move."
                print(self.computer_message)
                self.player_trun = True
                self.legal_moves = (
                    self.lm.find_all_moves("b", self.items)
                )
                return
            else:
                print(" ")
                ro, col = self.pick_best_move(self.legal_moves)

                self.computer_make_move(col, ro)
                # switch to computer turn
                # and calc computer legal moves
                self.player_trun = True
                self.legal_moves = (
                    self.lm.find_all_moves("b", self.items)
                )
                return

    def pick_best_move(self, legal_moves):
        """
        input: nested list legal move

        output: int row coord, int col coord

        compare the number of tiles the each move can flip,
        then return the coords of the legal move that can flip the most
        tiles.
        """
        move_dict = {}
        # create a dict where the keys are legal move coords and
        # values are a set of tile coords that will be flipped
        # if the move is chosen
        for move in legal_moves:
            move_coord = str(move[0]) + "-" + str(move[1])
            for tile in move[2]:
                tile_coord = str(tile[0]) + "-" + str(tile[1])

                if move_coord not in move_dict.keys():
                    content = {tile_coord}
                    move_dict[move_coord] = content
                else:
                    move_dict[move_coord].add(tile_coord)

        max_flip = 0
        choose_move = None

        # identify the dict key (legal move) and has the most
        # items stored in the value set
        for move in move_dict.keys():
            if len(move_dict[move]) > max_flip:
                max_flip = len(move_dict[move])
                row, col = move.split("-")

        # return the row and col coord of the move that
        # would flip the most tiles
        return int(row), int(col)

    def player_make_move(self, column, row):
        """
        input: int column index, int row index

        call add disk to baord method and call flip method
        """
        self.add_disk_to_board(column, row, "b")
        self.flip(column, row, "b")

    def computer_make_move(self, column, row):
        """
        input: int column index, int row index

        call add disk to baord method and call flip method
        """
        self.add_disk_to_board(column, row, "w")
        self.flip(column, row, "w")

    def add_disk_to_board(self, column, row, color):
        """
        input: int x coord, int y coord, str color

        assign tile color to the col and row index
        """

        self.items[row][column] = color

    def flip(self, column, row, color):
        """
        input: int col index, int row idex, str color

        Based on the col and row index, flip all tiles that are
        associated with that legal move to the str color passed in
        as argument
        """

        for legal_move in self.legal_moves:
            if legal_move[0] == row and legal_move[1] == column:
                for tile in legal_move[2]:
                    self.items[tile[0]][tile[1]] = color

    def display(self):
        """
        display the board and the tiles
        """
        # draw the board
        grid_dis = self.WIDTH//self.GRID_CT
        x = 0
        y = 0

        strokeWeight(self.STROKE_WEIGHT)

        # draw grid lines
        while x < self.WIDTH:
            x += grid_dis
            y += grid_dis
            line(x, 0, x, self.HEIGHT)
            line(0, y, self.WIDTH, y)

        # draw the disks
        disk_size = self.grid_size * self.DISK_FILL

        for row in range(self.GRID_CT):
            for col in range(self.GRID_CT):

                if self.items[row][col] == "w":
                    fill(self.WHITE)
                    x_coord = (col+1) * self.grid_size - self.half_grid
                    y_coord = (row+1) * self.grid_size - self.half_grid
                    ellipse(x_coord, y_coord, disk_size, disk_size)

                elif self.items[row][col] == "b":
                    fill(self.BLACK)
                    x_coord = (col + 1) * self.grid_size - self.half_grid
                    y_coord = (row + 1) * self.grid_size - self.half_grid
                    ellipse(x_coord, y_coord, disk_size, disk_size)

    def update(self, name):
        """
        assess if the game has ended,
        decide the winer of the game and prints out
        message to notify the players.
        """

        white_point = 0
        black_point = 0

        # count each color
        for row in self.items:
            white_point += row.count("w")
            black_point += row.count("b")

        tie_message = "Each side has " + str(white_point) + " tiles."
        white_win_message = "Computer has " + str(white_point) + " tiles."
        black_win_message = "You have " + str(black_point) + " tiles."

        # end game if board is full
        if white_point + black_point == self.GRID_CT * self.GRID_CT:
            self.game_end = True
            if white_point == black_point:
                print("Game Over")
                print("Its a tie!")
                print(tie_message)

            elif black_point > white_point:
                print("Game Over")
                print("You won!")
                print(black_win_message)

            else:
                print("Game Over")
                print("Computer won!")
                print(white_win_message)

        # end game when both sides are out of moves
        if self.player_message and self.computer_message:
            self.game_end = True
            if white_point == black_point:
                print("Game Over")
                print("Its a tie!")
                print(tie_message)

            elif black_point > white_point:
                print("Game Over")
                print("You won!")
                print(black_win_message)

            else:
                print("Game Over")
                print("Computer won!")
                print(white_win_message)

        if self.game_end:
            # open the scores txt file, if does not exist
            # create one
            try:
                out = open("scores.txt", "r")
            except IOError as e:
                out = open("scores.txt", "w+")

            # store first line
            content = out.readline()

            if len(content) > 0:
                content_list = content.split()
                # store max score if exists
                max_score = int(content_list[len(content_list)-1])
                out.close()

                if black_point <= max_score:
                    # open file to append new line
                    out = open("scores.txt", "a")
                    out.write(name + " " + str(black_point) + "\n")
                    out.close()
                else:
                    # create a new file, insert score at top,
                    # then add all data from old file
                    with open("scores.txt", "r") as f:
                        with open('newfile.txt', 'w') as f2:
                            f2.write(name + " " + str(black_point) + "\n")
                            f2.write(f.read())
                    # rename new file to old file name to replace new file
                    os.rename('newfile.txt', "scores.txt")

            else:
                out.close()
                # open file to append new line
                out = open("scores.txt", "a")
                out.write(name + " " + str(black_point) + "\n")
                out.close()
