package jazz.util;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

class Quadtree<E> {

  private static int M = 32;

  public static void main(String... args) {
    Quadtree<Integer> q = new Quadtree<>();
    q.insert(7, 3, 1);
    q.insert(7, 3, 2);
    q.insert(7, 3, 3);
    q.insert(3, 7, 4);
    q.insert(-1, -1, 5);
    q.insert(-1, -1, 6);
    q.insert(0, 0, 1337);
    q.delete(0, 0, 1337);
    System.out.println(q);
  }

  class Node {
    Node q1, q2, q3, q4;
    List<E> as;
    int ax;
    int ay;

    boolean isLeaf() {
      return q1 == null && q2 == null && q3 == null && q4 == null;
    }

    int numChildren() {
      int num = 0;
      num += q1 != null ? 1 : 0;
      num += q2 != null ? 1 : 0;
      num += q3 != null ? 1 : 0;
      num += q4 != null ? 1 : 0;
      return num;
    }

    String toString(StringBuilder b) {
      if (as != null) {
        b.append(as);
      } else {
        b.append("<table width='100%' height='100%'><tr><td>");
        b.append(q2 == null ? "&#160;" : q2.toString(b));
        b.append("</td><td>");
        b.append(q1 == null ? "&#160;" : q1.toString(b));
        b.append("</td></tr><tr><td>");
        b.append(q3 == null ? "&#160;" : q3.toString(b));
        b.append("</td><td>");
        b.append(q4 == null ? "&#160;" : q4.toString(b));
        b.append("</td></tr></table>");
      }
      return "";
    }
  }

  Node root = new Node();

  Node select(Node c, int i, int x, int y) {
    int j = (1 << (M - 1)) >>> i;
    if ((x & j) == j) {
      return (y & j) == j ? c.q3 : c.q2;
    } else {
      return (y & j) == j ? c.q4 : c.q1;
    }
  }

  Node insert(Node c, int i, int x, int y) {

    i = (1 << (M - 1)) >>> i;

    if ((x & i) == i) {
      if ((y & i) == i) {
        return c.q3 == null ? (c.q3 = new Node()) : c.q3;
      }
      return c.q2 == null ? (c.q2 = new Node()) : c.q2;
    } else {
      if ((y & i) == i) {
        return c.q4 == null ? (c.q4 = new Node()) : c.q4;
      }
      return c.q1 == null ? (c.q1 = new Node()) : c.q1;
    }
  }

  public List<E> at(int x, int y) {

    Node c = root;

    for (int i = 0; i < M; i++) {
      if (c.isLeaf()) {
        if (c.ax == x && c.ay == y) {
          return Collections.unmodifiableList(c.as);
        }
        return null;
      }
      if ((c = select(c, i, x, y)) == null) {
        return Collections.emptyList();
      }
    }
    if (c.as != null) {
      return Collections.unmodifiableList(c.as);
    }
    return Collections.emptyList();
  }

  public void insert(int x, int y, E e) {

    Node c = root;

    for (int i = 0; i < M; i++) {
      if (c.isLeaf()) {
        if (c.as == null) {
          c.as = new ArrayList<E>(2);
          c.as.add(e);
          c.ax = x;
          c.ay = y;
          return;
        }
        Node n = insert(c, i, c.ax, c.ay);
        n.as = c.as;
        n.ax = c.ax;
        n.ay = c.ay;
        c.as = null;
      }
      c = insert(c, i, x, y);
    }
    c.as.add(e);
  }

  public void delete(int x, int y, E e) {

    Node c = root;

    Deque<Node> path = new ArrayDeque<Node>(M);
    for (int i = 0; i < M; i++) {
      if (c.isLeaf()) {
        if (c.ax == x && c.ay == y) {
          break;
        }
        return;
      }
      if ((c = select(c, i, x, y)) == null) {
        return;
      }
      path.push(c);
    }
    if (c.as != null) {
      c.as.remove(e);
      if (c.as.isEmpty()) {
        c.as = null;
      }
    }
  }

  public String toString() {
    StringBuilder b = new StringBuilder();
    b.append("<style>table { border-collapse: collapse; } td { margin: 0; padding: 0; border: 1px solid gray; }</style>");
    root.toString(b);
    return b.toString();
  }
}
