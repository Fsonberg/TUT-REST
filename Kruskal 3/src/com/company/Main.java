package com.company;

public class Main {
    public static void main(String[] args) {
        // configure the width and height
        int w = Maze.DEFAULT_WIDTH;
        int h = Maze.DEFAULT_HEIGHT;
        long seed = 0L;
        boolean useSeed = false;
        boolean animate = false;
        float delay = 0.04f;

        // ignore arguments that we don't understand
        for ( int i=0; i < args.length; ++i ) {
            String arg = args[i];

            // parse the "parameter" arguments
            if ( arg.length() > 2 ) {
                String s = arg.substring(0,1);
                String t = arg.substring(1,2);
                if ( s.equals("-")) {
                    if ( t.equals("w") ) {
                        w = Integer.parseInt(arg.substring(2));
                    }
                    else if ( t.equals("h") ) {
                        h = Integer.parseInt(arg.substring(2));
                    }
                    else if ( t.equals("s") ) {
                        useSeed = true;
                        seed = Long.parseLong(arg.substring(2));
                    }
                    else if ( t.equals("d") ) {
                        delay = Float.parseFloat(arg.substring(2));
                    }
                }
            }

            // parse the "no parameter" arguments
            else if ( arg.length() > 1) {
                String s = arg.substring(0,1);
                String t = arg.substring(1,2);
                if ( s.equals("-") ) {
                    if ( t.equals("a") ) {
                        animate = true;
                    }
                }
            }
        }

        // render the maze
        if ( useSeed ) {
            new Kruskal(w,h,animate,delay,seed).draw();
        } else {
            new Kruskal(w,h,animate,delay).draw();
        }
    }
}

