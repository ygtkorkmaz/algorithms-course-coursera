/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        while (true) {
            String line = StdIn.readString();
            if (line == null) break;
            queue.enqueue(line);
        }

        Iterator<String> iterator = queue.iterator();
        for (int i = k; i > 0; i--) {
            StdOut.println(iterator.next());
        }

    }
}
