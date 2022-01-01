public class proccessing {
    public static float proccess(String block, int movement, boolean jumping, boolean headhitters) {
        if (movement == 1) {
            if (block.equals("solid")) {
                return 4.317f;
            } if (block.equals("ice")) {
                 
            }
        } else if (movement == 2) {
            if (jumping == true) {
                if (headhitters == true) {
                    if (block.equals("ice")) {
                        return 16.9f;
                    }
                }
            }
        } else if (movement == 3) {

        } 
        return 0.0f;
    }
}
