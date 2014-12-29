package com.github.fge.lambdas.suppliers;

import com.github.fge.lambdas.ThrowablesFactory;
import com.github.fge.lambdas.ThrowingFunctionalInterface;
import com.github.fge.lambdas.ThrownByLambdaException;

import java.util.function.IntSupplier;

/**
 * A throwing {@link IntSupplier}
 */
@FunctionalInterface
public interface ThrowingIntSupplier
    extends IntSupplier,
    ThrowingFunctionalInterface<ThrowingIntSupplier, IntSupplier>
{
    int doGetAsInt()
        throws Throwable;

    @Override
    default int getAsInt()
    {
        try {
            return doGetAsInt();
        } catch (Error | RuntimeException e) {
            throw e;
        } catch (Throwable tooBad) {
            throw new ThrownByLambdaException(tooBad);
        }
    }

    @Override
    default ThrowingIntSupplier orTryWith(ThrowingIntSupplier other)
    {
        return () -> {
            try {
                return doGetAsInt();
            } catch (Error | RuntimeException e) {
                throw e;
            } catch (Throwable ignored) {
                return other.doGetAsInt();
            }
        };
    }

    @Override
    default IntSupplier fallbackTo(IntSupplier fallback)
    {
        return () -> {
            try {
                return doGetAsInt();
            } catch (Error | RuntimeException e) {
                throw e;
            } catch (Throwable ignored) {
                return fallback.getAsInt();
            }
        };
    }

    default IntSupplier orReturn(int defaultValue)
    {
        return () -> {
            try {
                return doGetAsInt();
            } catch (Error | RuntimeException e) {
                throw e;
            } catch (Throwable ignored) {
                return defaultValue;
            }
        };
    }

    @Override
    default <E extends RuntimeException> IntSupplier orThrow(
        Class<E> exceptionClass)
    {
        return () -> {
            try {
                return doGetAsInt();
            } catch (Error | RuntimeException e) {
                throw e;
            } catch (Throwable tooBad) {
                throw ThrowablesFactory.INSTANCE.get(exceptionClass, tooBad);
            }
        };
    }
}
