public class Util implements Constants {
    public static Vector2 IndexToPosition(int row_index, int column_index) {
        return new Vector2(column_index * BLOCK_WIDTH + MARGIN_HORIZONTAL + BLOCK_WIDTH / 2,
                row_index * BLOCK_HEIGHT + MARGIN_VERTICAL + 50 + BLOCK_HEIGHT / 2);
    }
}
