from game_controller import GameController


WIDTH = 800
HEIGHT = 800
RED = 5
GREEN = 73
BLUE = 7
GRID_CT = 8
DISK_FILL = .95
mouse_x = 0
mouse_y = 0
count_down = 0
answer = None

gc = GameController(WIDTH, HEIGHT, GRID_CT, DISK_FILL)


def setup():
    """
    set up canvas
    """
    size(WIDTH, HEIGHT)
    background(RED, GREEN, BLUE)

    global answer
    answer = input('enter your name')
    if answer:
        print('hi ' + answer)
    elif answer == '':
        print('[empty string]')
    else:
        print(answer)


def draw():
    """
    draw items to canvas
    """

    if gc.game_end:
        gc.display()
    else:
        gc.display()

        global count_down
        # if player's turn and if mouse pressed,
        # call the decide turn method and pass in
        # the mouse coords
        if gc.player_trun:
            if mouse_x > 0:
                gc.decide_turn(mouse_x, mouse_y)
                count_down = 10000
            gc.update(answer)

        # if computer turn, deplay the program with
        # while loop, then call decide turn method
        # and pass in filler arguments
        else:
            while count_down > 0:
                count_down -= 1
            gc.decide_turn(0, 0)
            gc.update(answer)


def mousePressed():
    """
    when the mouse is pressed, assign the mouse
    coords as global variables
    """
    global mouse_x, mouse_y
    mouse_x = mouseX
    mouse_y = mouseY


def input(message=''):
    from javax.swing import JOptionPane
    return JOptionPane.showInputDialog(frame, message)
