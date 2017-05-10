package com.gmail.radekzatec77.tictactoe.resolver;

import java.util.Iterator;

public class OffsetArrayIterator<T> implements Iterator<T>, Iterable<T> {

  private final T[] array;
  private final int from;
  private final int to;
  private int current = 0;

  public OffsetArrayIterator(T[] array, int start, int offset) {
    this.array = array;
    this.from = (start - offset < 0) ? 0 : start - offset;
    this.to = (start + offset + 1> array.length) ? array.length : start + offset + 1;
    this.current = from;
  }

  public boolean hasNext() {
    return current < to;
  }

  public T next() {
    return array[current++];
  }

  public void remove() {
    // this is not needed
  }

  public Iterator<T> iterator() {
    return this;
  }
}
