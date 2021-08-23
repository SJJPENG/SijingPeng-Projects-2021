class LegalMove:

    def __init__(self, GRID_CT):
        """
        set up constant variable and empty list
        """
        self.moves = []
        self.GRID_CT = GRID_CT

    def find_all_moves(self, color, items):
        """
        input: str color, list items
        output: nested list legal moves

        for each tile of the color argument, call the check
        surrounding method to check if the surrounding cells
        are legal moves
        """
        # remove the old moves
        self.moves = []

        check_cell = None

        # for each oppo color tile, check the surrounding cells for legal moves
        for row_index in range(len(items)):
            for column_index in range(len(items[row_index])):
                if (
                    items[row_index][column_index] != 0 and
                    items[row_index][column_index] != color
                ):
                    check_cell = self.check_surrounding(
                        items, row_index, column_index, color
                    )

        return self.moves

    def check_surrounding(self, disk_items, row, col, color):
        """
        input: list disk items, int row index, int col index, str color

        check each of the 8 surrounding cells of the tile in col, row index
        and append any legal moves to the moves list
        """
        # check the 8 surrounding cells and see if each cell is a legal move
        if self.check_cell_up(disk_items, row, col, color):
            self.moves.append(
                self.check_cell_up(disk_items, row, col, color)
            )

        if self.check_cell_down(disk_items, row, col, color):
            self.moves.append(
                self.check_cell_down(disk_items, row, col, color)
            )

        if self.check_cell_left(disk_items, row, col, color):
            self.moves.append(
                self.check_cell_left(disk_items, row, col, color)
            )

        if self.check_cell_right(disk_items, row, col, color):
            self.moves.append(
                self.check_cell_right(disk_items, row, col, color)
            )

        if self.check_cell_up_left(disk_items, row, col, color):
            self.moves.append(
                self.check_cell_up_left(disk_items, row, col, color)
            )

        if self.check_cell_up_right(disk_items, row, col, color):
            self.moves.append(
                self.check_cell_up_right(disk_items, row, col, color)
            )

        if self.check_cell_lower_left(disk_items, row, col, color):
            self.moves.append(
                self.check_cell_lower_left(disk_items, row, col, color)
            )

        if self.check_cell_lower_right(disk_items, row, col, color):
            self.moves.append(
                self.check_cell_lower_right(disk_items, row, col, color)
            )

    def check_cell_up(self, disk_items, row, col, color):
        """
        input: list disk items, int row index, int col index, str color
        return: list move information

        check the upper cell of the tile in col, row index
        and return legal move information if exists
        """
        # check one cell up
        # if the cell right next to the tile is empty, and there exist
        # an opposite color tile in the opposite direction in between
        # the empty cell and the color tile(s), the empty cell is
        # a legal move
        temp_row = 0
        if len(disk_items) > row-1 >= 0 and disk_items[row-1][col] == 0:
            temp_row = row
            while 0 <= temp_row < len(disk_items) - 1:
                temp_row += 1
                if disk_items[temp_row][col] == 0:
                    return None
                elif disk_items[temp_row][col] == color:
                    tiles = []
                    for r in range(row, temp_row + 1):
                        tiles.append([r, col])
                    return [row-1, col, tiles, "up"]

        else:
            return None

    def check_cell_down(self, disk_items, row, col, color):
        """
        input: list disk items, int row index, int col index, str color
        return: list move information

        check the lower cell of the tile in col, row index
        and return legal move information if exists
        """
        # check one cell down
        # if the cell right next to the tile is empty, and there exist
        # an opposite color tile in the opposite direction in between
        # the empty cell and the color tile(s), the empty cell is
        # a legal move
        temp_row = 0
        if len(disk_items) > row+1 >= 0 and disk_items[row+1][col] == 0:
            temp_row = row
            while 1 <= temp_row < len(disk_items) - 1:
                temp_row -= 1
                if disk_items[temp_row][col] == 0:
                    return None
                elif disk_items[temp_row][col] == color:
                    tiles = []
                    for r in range(temp_row, row+1):
                        tiles.append([r, col])
                    return [row+1, col, tiles, "down"]

    def check_cell_left(self, disk_items, row, col, color):
        """
        input: list disk items, int row index, int col index, str color
        return: list move information

        check the left cell of the tile in col, row index
        and return legal move information if exists
        """
        # check one cell left
        # if the cell right next to the tile is empty, and there exist
        # an opposite color tile in the opposite direction in between
        # the empty cell and the color tile(s), the empty cell is
        # a legal move
        temp_col = 0
        if len(disk_items) > col-1 >= 0 and disk_items[row][col-1] == 0:
            temp_col = col
            while 0 <= temp_col < len(disk_items) - 1:
                temp_col += 1
                if disk_items[row][temp_col] == 0:
                    return None
                elif disk_items[row][temp_col] == color:
                    tiles = []
                    for c in range(col, temp_col+1):
                        tiles.append([row, c])
                    return [row, col-1, tiles, "left"]

    def check_cell_right(self, disk_items, row, col, color):
        """
        input: list disk items, int row index, int col index, str color
        return: list move information

        check the right cell of the tile in col, row index
        and return legal move information if exists
        """
        # check one cell right
        # if the cell right next to the tile is empty, and there exist
        # an opposite color tile in the opposite direction in between
        # the empty cell and the color tile(s), the empty cell is
        # a legal move
        temp_col = 0
        if len(disk_items) > col+1 >= 0 and disk_items[row][col+1] == 0:
            temp_col = col
            while 1 <= temp_col < len(disk_items) - 1:
                temp_col -= 1
                if disk_items[row][temp_col] == 0:
                    return None
                elif disk_items[row][temp_col] == color:
                    tiles = []
                    for c in range(temp_col, col+1):
                        tiles.append([row, c])
                    return [row, col+1, tiles, "right"]

    def check_cell_up_left(self, disk_items, row, col, color):
        """
        input: list disk items, int row index, int col index, str color
        return: list move information

        check the upper left cell of the tile in col, row index
        and return legal move information if exists
        """
        # check one cell right
        # if the cell right next to the tile is empty, and there exist
        # an opposite color tile in the opposite direction in between
        # the empty cell and the color tile(s), the empty cell is
        # a legal move
        temp_row = 0
        temp_col = 0
        if (
            len(disk_items) > row-1 >= 0 and
            len(disk_items) > col-1 >= 0 and
            disk_items[row-1][col-1] == 0
        ):
            temp_col = col
            temp_row = row

            while (
                0 <= temp_col < len(disk_items) - 1 and
                0 <= temp_row < len(disk_items) - 1
            ):
                temp_row += 1
                temp_col += 1
                if disk_items[temp_row][temp_col] == 0:
                    return None
                elif disk_items[temp_row][temp_col] == color:
                    tiles = []
                    r = row
                    for c in range(col, temp_col+1):
                        tiles.append([r, c])
                        r += 1
                    return [row-1, col-1, tiles, "up left"]

    def check_cell_up_right(self, disk_items, row, col, color):
        """
        input: list disk items, int row index, int col index, str color
        return: list move information

        check the upper right cell of the tile in col, row index
        and return legal move information if exists
        """
        # check one cell right
        # if the cell right next to the tile is empty, and there exist
        # an opposite color tile in the opposite direction in between
        # the empty cell and the color tile(s), the empty cell is
        # a legal move
        temp_row = 0
        temp_col = 0
        if (
            len(disk_items) > row-1 >= 0 and
            len(disk_items) > col+1 >= 0 and
            disk_items[row-1][col+1] == 0
        ):
            temp_col = col
            temp_row = row

            while (
                1 <= temp_col < len(disk_items) - 1 and
                0 <= temp_row < len(disk_items) - 1
            ):
                temp_row += 1
                temp_col -= 1
                if disk_items[temp_row][temp_col] == 0:
                    return None
                elif disk_items[temp_row][temp_col] == color:
                    tiles = []
                    r = row
                    for c in range(col, temp_col-1, -1):
                        tiles.append([r, c])
                        r += 1
                    return [row-1, col+1, tiles, "up right"]

    def check_cell_lower_left(self, disk_items, row, col, color):
        """
        input: list disk items, int row index, int col index, str color
        return: list move information

        check the lower left cell of the tile in col, row index
        and return legal move information if exists
        """
        # check one cell right
        # if the cell right next to the tile is empty, and there exist
        # an opposite color tile in the opposite direction in between
        # the empty cell and the color tile(s), the empty cell is
        # a legal move
        temp_row = 0
        temp_col = 0
        if (
            len(disk_items) > row+1 >= 0 and
            len(disk_items) > col-1 >= 0 and
            disk_items[row+1][col-1] == 0
        ):
            temp_col = col
            temp_row = row

            while (
                0 <= temp_col < len(disk_items) - 1 and
                1 <= temp_row < len(disk_items) - 1
            ):
                temp_row -= 1
                temp_col += 1
                if disk_items[temp_row][temp_col] == 0:
                    return None
                elif disk_items[temp_row][temp_col] == color:
                    tiles = []
                    r = row
                    for c in range(col, temp_col+1):
                        tiles.append([r, c])
                        r -= 1
                    return [row+1, col-1, tiles, "lower left"]

    def check_cell_lower_right(self, disk_items, row, col, color):
        """
        input: list disk items, int row index, int col index, str color
        return: list move information

        check the upper right cell of the tile in col, row index
        and return legal move information if exists
        """
        # check one cell right
        # if the cell right next to the tile is empty, and there exist
        # an opposite color tile in the opposite direction in between
        # the empty cell and the color tile(s), the empty cell is
        # a legal move
        temp_row = 0
        temp_col = 0
        if (
            len(disk_items) > row+1 >= 0 and
            len(disk_items) > col+1 >= 0 and
            disk_items[row+1][col+1] == 0
        ):
            temp_col = col
            temp_row = row

            while (
                0 <= temp_col < len(disk_items) - 1 and
                0 <= temp_row < len(disk_items) - 1
            ):
                temp_row -= 1
                temp_col -= 1
                if disk_items[temp_row][temp_col] == 0:
                    return None
                elif disk_items[temp_row][temp_col] == color:
                    tiles = []
                    r = row
                    for c in range(col, temp_col-1, -1):
                        tiles.append([r, c])
                        r -= 1
                    return [row+1, col+1, tiles, "lower right"]
