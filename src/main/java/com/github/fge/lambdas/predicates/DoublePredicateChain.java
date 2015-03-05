package com.github.fge.lambdas.predicates;

import com.github.fge.lambdas.Chain;
import com.github.fge.lambdas.ThrowablesFactory;

import java.util.function.DoublePredicate;

public class DoublePredicateChain
    extends Chain<DoublePredicate, ThrowingDoublePredicate, DoublePredicateChain>
    implements ThrowingDoublePredicate
{
    public DoublePredicateChain(
        final ThrowingDoublePredicate throwing)
    {
        super(throwing);
    }

    @Override
    public boolean doTest(final double value)
        throws Throwable
    {
        return throwing.doTest(value);
    }

    @Override
    public DoublePredicateChain orTryWith(final ThrowingDoublePredicate other)
    {
        final ThrowingDoublePredicate doublePredicate = value -> {
            try {
                return throwing.doTest(value);
            } catch (Error | RuntimeException e) {
                throw e;
            } catch (Throwable ignored) {
                return other.doTest(value);
            }
        };

        return new DoublePredicateChain(doublePredicate);
    }

    @Override
    public <E extends RuntimeException> ThrowingDoublePredicate orThrow(
        final Class<E> exclass)
    {
        return value -> {
            try {
                return throwing.doTest(value);
            } catch (Error | RuntimeException e) {
                throw e;
            } catch (Throwable throwable) {
                throw ThrowablesFactory.INSTANCE.get(exclass, throwable);
            }
        };
    }

    @Override
    public DoublePredicate fallbackTo(final DoublePredicate fallback)
    {
        return value -> {
            try {
                return throwing.doTest(value);
            } catch (Error | RuntimeException e) {
                throw e;
            } catch (Throwable ignored) {
                return fallback.test(value);
            }
        };
    }

    public DoublePredicate orReturnTrue()
    {
        return value -> {
            try {
                return throwing.doTest(value);
            } catch (Error | RuntimeException e) {
                throw e;
            } catch (Throwable ignored) {
                return true;
            }
        };
    }

    public DoublePredicate orReturnFalse()
    {
        return value -> {
            try {
                return throwing.doTest(value);
            } catch (Error | RuntimeException e) {
                throw e;
            } catch (Throwable ignored) {
                return false;
            }
        };
    }
}