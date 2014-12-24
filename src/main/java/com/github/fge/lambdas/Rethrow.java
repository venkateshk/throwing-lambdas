package com.github.fge.lambdas;


import com.github.fge.lambdas.consumers.ThrowingConsumer;
import com.github.fge.lambdas.consumers.ThrowingDoubleConsumer;
import com.github.fge.lambdas.consumers.ThrowingIntConsumer;
import com.github.fge.lambdas.consumers.ThrowingLongConsumer;
import com.github.fge.lambdas.consumers.twoarity.ThrowingBiConsumer;
import com.github.fge.lambdas.functions.ThrowingFunction;
import com.github.fge.lambdas.functions.ThrowingUnaryOperator;
import com.github.fge.lambdas.functions.doublefunctions.ThrowingDoubleFunction;
import com.github.fge.lambdas.functions.doublefunctions
    .ThrowingDoubleToIntFunction;
import com.github.fge.lambdas.functions.doublefunctions
    .ThrowingDoubleToLongFunction;
import com.github.fge.lambdas.functions.intfunctions.ThrowingIntFunction;
import com.github.fge.lambdas.functions.intfunctions
    .ThrowingIntToDoubleFunction;
import com.github.fge.lambdas.functions.intfunctions.ThrowingIntToLongFunction;
import com.github.fge.lambdas.functions.longfunctions.ThrowingLongFunction;
import com.github.fge.lambdas.functions.longfunctions
    .ThrowingLongToDoubleFunction;
import com.github.fge.lambdas.functions.longfunctions.ThrowingLongToIntFunction;
import com.github.fge.lambdas.functions.twoarity.ThrowingBiFunction;
import com.github.fge.lambdas.functions.twoarity.ThrowingBinaryOperator;
import com.github.fge.lambdas.predicates.ThrowingDoublePredicate;
import com.github.fge.lambdas.predicates.ThrowingIntPredicate;
import com.github.fge.lambdas.predicates.ThrowingLongPredicate;
import com.github.fge.lambdas.predicates.ThrowingPredicate;
import com.github.fge.lambdas.suppliers.ThrowingDoubleSupplier;
import com.github.fge.lambdas.suppliers.ThrowingIntSupplier;
import com.github.fge.lambdas.suppliers.ThrowingLongSupplier;
import com.github.fge.lambdas.suppliers.ThrowingSupplier;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.DoubleSupplier;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntSupplier;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;
import java.util.function.LongPredicate;
import java.util.function.LongSupplier;
import java.util.function.LongToDoubleFunction;
import java.util.function.LongToIntFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

@SuppressWarnings("NonFinalUtilityClass")
public class Rethrow
{
    /*
     * FUNCTIONS
     */

    public static <T, R> Function<T, R> rethrow(final ThrowingFunction<T, R> f)
    {
        return t -> {
            try {
                return f.apply(t);
            } catch (Error | RuntimeException e) {
                throw e;
            } catch (Throwable tooBad) {
                throw new ThrownByLambdaException(tooBad);
            }
        };
    }

    public static <T> UnaryOperator<T> rethrow(final ThrowingUnaryOperator<T> o)
    {
        return t -> {
            try {
                return o.apply(t);
            } catch (Error | RuntimeException e) {
                throw e;
            } catch (Throwable tooBad) {
                throw new ThrownByLambdaException(tooBad);
            }
        };
    }

    public static <R> IntFunction<R> rethrow(final ThrowingIntFunction<R> f)
    {
        return t -> {
            try {
                return f.apply(t);
            } catch (Error | RuntimeException e) {
                throw e;
            } catch (Throwable tooBad) {
                throw new ThrownByLambdaException(tooBad);
            }
        };
    }

    public static <R> LongFunction<R> rethrow(final ThrowingLongFunction<R> f)
    {
        return t -> {
            try {
                return f.apply(t);
            } catch (Error | RuntimeException e) {
                throw e;
            } catch (Throwable tooBad) {
                throw new ThrownByLambdaException(tooBad);
            }
        };
    }

    public static <R> DoubleFunction<R> rethrow(
        final ThrowingDoubleFunction<R> f)
    {
        return t -> {
            try {
                return f.apply(t);
            } catch (Error | RuntimeException e) {
                throw e;
            } catch (Throwable tooBad) {
                throw new ThrownByLambdaException(tooBad);
            }
        };
    }

    public static IntToLongFunction rethrow(final ThrowingIntToLongFunction f)
    {
        return t -> {
            try {
                return f.apply(t);
            } catch (Error | RuntimeException e) {
                throw e;
            } catch (Throwable tooBad) {
                throw new ThrownByLambdaException(tooBad);
            }
        };
    }

    public static IntToDoubleFunction rethrow(
        final ThrowingIntToDoubleFunction f)
    {
        return t -> {
            try {
                return f.apply(t);
            } catch (Error | RuntimeException e) {
                throw e;
            } catch (Throwable tooBad) {
                throw new ThrownByLambdaException(tooBad);
            }
        };
    }

    public static LongToIntFunction rethrow(final ThrowingLongToIntFunction f)
    {
        return t -> {
            try {
                return f.apply(t);
            } catch (Error | RuntimeException e) {
                throw e;
            } catch (Throwable tooBad) {
                throw new ThrownByLambdaException(tooBad);
            }
        };
    }

    public static LongToDoubleFunction rethrow(
        final ThrowingLongToDoubleFunction f)
    {
        return t -> {
            try {
                return f.apply(t);
            } catch (Error | RuntimeException e) {
                throw e;
            } catch (Throwable tooBad) {
                throw new ThrownByLambdaException(tooBad);
            }
        };
    }

    public static DoubleToIntFunction rethrow(
        final ThrowingDoubleToIntFunction f)
    {
        return t -> {
            try {
                return f.apply(t);
            } catch (Error | RuntimeException e) {
                throw e;
            } catch (Throwable tooBad) {
                throw new ThrownByLambdaException(tooBad);
            }
        };
    }

    public static DoubleToLongFunction rethrow(
        final ThrowingDoubleToLongFunction f)
    {
        return t -> {
            try {
                return f.apply(t);
            } catch (Error | RuntimeException e) {
                throw e;
            } catch (Throwable tooBad) {
                throw new ThrownByLambdaException(tooBad);
            }
        };
    }

    public static <T> Predicate<T> rethrow(final ThrowingPredicate<T> p)
    {
        return t -> {
            try {
                return p.test(t);
            } catch (Error | RuntimeException e) {
                throw e;
            } catch (Throwable tooBad) {
                throw new ThrownByLambdaException(tooBad);
            }
        };
    }

    public static IntPredicate rethrow(final ThrowingIntPredicate p)
    {
        return t -> {
            try {
                return p.test(t);
            } catch (Error | RuntimeException e) {
                throw e;
            } catch (Throwable tooBad) {
                throw new ThrownByLambdaException(tooBad);
            }
        };
    }

    public static LongPredicate rethrow(final ThrowingLongPredicate p)
    {
        return t -> {
            try {
                return p.test(t);
            } catch (Error | RuntimeException e) {
                throw e;
            } catch (Throwable tooBad) {
                throw new ThrownByLambdaException(tooBad);
            }
        };
    }

    public static DoublePredicate rethrow(final ThrowingDoublePredicate p)
    {
        return t -> {
            try {
                return p.test(t);
            } catch (Error | RuntimeException e) {
                throw e;
            } catch (Throwable tooBad) {
                throw new ThrownByLambdaException(tooBad);
            }
        };
    }

    /*
     * CONSUMERS
     */

    public static <T> Consumer<T> rethrow(final ThrowingConsumer<T> c)
    {
        return t -> {
            try {
                c.accept(t);
            } catch (Error | RuntimeException e) {
                throw e;
            } catch (Throwable tooBad) {
                throw new ThrownByLambdaException(tooBad);
            }
        };
    }

    public static IntConsumer rethrow(final ThrowingIntConsumer c)
    {
        return t -> {
            try {
                c.accept(t);
            } catch (Error | RuntimeException e) {
                throw e;
            } catch (Throwable tooBad) {
                throw new ThrownByLambdaException(tooBad);
            }
        };
    }

    public static LongConsumer rethrow(final ThrowingLongConsumer c)
    {
        return t -> {
            try {
                c.accept(t);
            } catch (Error | RuntimeException e) {
                throw e;
            } catch (Throwable tooBad) {
                throw new ThrownByLambdaException(tooBad);
            }
        };
    }

    public static DoubleConsumer rethrow(final ThrowingDoubleConsumer c)
    {
        return t -> {
            try {
                c.accept(t);
            } catch (Error | RuntimeException e) {
                throw e;
            } catch (Throwable tooBad) {
                throw new ThrownByLambdaException(tooBad);
            }
        };
    }

    /*
     * SUPPLIERS
     */

    public static <T> Supplier<T> rethrow(final ThrowingSupplier<T> s) {
        return () -> {
            try {
                return s.get();
            } catch (Error | RuntimeException e) {
                throw e;
            } catch (Throwable tooBad) {
                throw new ThrownByLambdaException(tooBad);
            }
        };
    }

    public static IntSupplier rethrow(final ThrowingIntSupplier s) {
        return () -> {
            try {
                return s.getAsInt();
            } catch (Error | RuntimeException e) {
                throw e;
            } catch (Throwable tooBad) {
                throw new ThrownByLambdaException(tooBad);
            }
        };
    }

    public static LongSupplier rethrow(final ThrowingLongSupplier s) {
        return () -> {
            try {
                return s.getAsLong();
            } catch (Error | RuntimeException e) {
                throw e;
            } catch (Throwable tooBad) {
                throw new ThrownByLambdaException(tooBad);
            }
        };
    }

    public static DoubleSupplier rethrow(final ThrowingDoubleSupplier s) {
        return () -> {
            try {
                return s.getAsDouble();
            } catch (Error | RuntimeException e) {
                throw e;
            } catch (Throwable tooBad) {
                throw new ThrownByLambdaException(tooBad);
            }
        };
    }

    /*
     * TWO-ARITY CONSUMERS
     */

    public static <T, U> BiConsumer<T, U> rethrow(
        final ThrowingBiConsumer<T, U> c)
    {
        return (t, u) -> {
            try {
                c.accept(t, u);
            } catch (Error | RuntimeException e) {
                throw e;
            } catch (Throwable tooBad) {
                throw new ThrownByLambdaException(tooBad);
            }
        };
    }

    /*
     * TWO-ARITY FUNCTIONS
     */

    public static <T, U, R> BiFunction<T, U, R> rethrow(
        final ThrowingBiFunction<T, U, R> f)
    {
        return (t, u) -> {
            try {
                return f.apply(t, u);
            } catch (Error | RuntimeException e) {
                throw e;
            } catch (Throwable tooBad) {
                throw new ThrownByLambdaException(tooBad);
            }
        };
    }

    public static <T> BinaryOperator<T> rethrow(
        final ThrowingBinaryOperator<T> o)
    {
        return (t1, t2) -> {
            try {
                return o.apply(t1, t2);
            } catch (Error | RuntimeException e) {
                throw e;
            } catch (Throwable tooBad) {
                throw new ThrownByLambdaException(tooBad);
            }
        };
    }
}
