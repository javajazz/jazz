package de.scravy.jazz;

public interface UpdateHandler<M> {

  M update(final M model, final double time, final double delta);
}
