public interface Constants {

    int ITEM_NONE = 0;
    int ITEM_PLAYER = 1;
    int ITEM_LASER = 2;
    int ITEM_ENLARGE = 3;
    int ITEM_CATCH = 4;
    int ITEM_SLOW = 5;
    int ITEM_BREAK = 6;
    int ITEM_DISRUPTION = 7;

    int BLOCK_GRAY = 50;
    int BLOCK_ORANGE = 60;
    int BLOCK_CYON = 70;
    int BLOCK_GREEN = 80;
    int BLOCK_RED = 90;
    int BLOCK_BLUE = 100;
    int BLOCK_PINK = 110;
    int BLOCK_YELLOW = 120;
    int BLOCK_HARD = 500;
    int BLOCK_GOLD = 600;

    int BLOCK_WIDTH = 30;
    int BLOCK_HEIGHT = 15;

    int BALL_RADIUS =5;

    int WINDOW_WIDTH = 480;
    int WINDOW_HEIGHT = 800;

    int MARGIN_HORIZONTAL = (WINDOW_WIDTH - (BLOCK_WIDTH * 13)) / 2;
    int MARGIN_VERTICAL = 40;

    int NORMAL_VAUS = 300;
    int LASER_VAUS = 301;
    int EXTENDED_VAUS = 302;

    int VAUS_WIDTH = 60;
    int VAUS_HEIGHT = 15;
    int VAUS_SPEED = 5;

    int WALL = 305;
}
