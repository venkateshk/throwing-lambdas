package com.github.fge.lambdas.consumers;

import com.github.fge.lambdas.Chain;
import com.github.fge.lambdas.ThrowablesFactory;

import java.util.function.ObjLongConsumer;

public class ObjLongConsumerChain<T>
    extends Chain<ObjLongConsumer<T>, ThrowingObjLongConsumer<T>, ObjLongConsumerChain<T>>
    implements ThrowingObjLongConsumer<T>
{
    public ObjLongConsumerChain(
        final ThrowingObjLongConsumer<T> throwing)
    {
        super(throwing);
    }

    @Override
    public void doAccept(final T t, final long value)
        throws Throwable
    {
        throwing.doAccept(t, value);
    }

    @Override
    public ObjLongConsumerChain<T> orTryWith(
        final ThrowingObjLongConsumer<T> other)
    {
        final ThrowingObjLongConsumer<T> consumer = (t, value) -> {
            try {
                throwing.doAccept(t, value);
            } catch (Error | RuntimeException e) {
                throw e;
            } catch (Throwable ignored) {
                other.doAccept(t, value);
            }
        };

        return new ObjLongConsumerChain<>(consumer);
    }

    @Override
    public <E extends RuntimeException> ThrowingObjLongConsumer<T> orThrow(
        final Class<E> exclass)
    {
        return (t, value) -> {
            try {
                throwing.doAccept(t, value);
            } catch (Error | RuntimeException e) {
                throw e;
            } catch (Throwable throwable) {
                throw ThrowablesFactory.INSTANCE.get(exclass, throwable);
            }
        };
    }

    @Override
    public ObjLongConsumer<T> fallbackTo(final ObjLongConsumer<T> fallback)
    {
        return (t, value) -> {
            try {
                throwing.doAccept(t, value);
            } catch (Error | RuntimeException e) {
                throw e;
            } catch (Throwable ignored) {
                fallback.accept(t, value);
            }
        };
    }

    public ObjLongConsumer<T> orDoNothing()
    {
        return (t, value) -> {
            try {
                throwing.doAccept(t, value);
            } catch (Error | RuntimeException e) {
                throw e;
            } catch (Throwable ignored) {
                // nothing
            }
        };
    }
}
