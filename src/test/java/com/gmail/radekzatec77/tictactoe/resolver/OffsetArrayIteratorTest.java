package com.gmail.radekzatec77.tictactoe.resolver;

import org.junit.Test;

import static org.junit.Assert.*;

public class OffsetArrayIteratorTest {
  Integer[] testArray = new Integer[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};


  @Test
  public void itIteratesRight() throws Exception {
    OffsetArrayIterator<Integer> iterator = new OffsetArrayIterator<Integer>(testArray, 5, 2);
    assertSame(3, iterator.next());
    assertSame(4, iterator.next());
    assertSame(5, iterator.next());
    assertSame(6, iterator.next());
    assertSame(7, iterator.next());
    assertSame(false, iterator.hasNext());
  }

}