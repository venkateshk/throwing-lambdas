package com.github.fge.lambdas.functions;

import com.github.fge.lambdas.ThrowingInterfaceBaseTest;
import com.github.fge.lambdas.ThrownByLambdaException;
import com.github.fge.lambdas.helpers.MyException;
import com.github.fge.lambdas.helpers.Type1;
import com.github.fge.lambdas.helpers.Type2;

import java.util.concurrent.Callable;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SuppressWarnings({ "ProhibitedExceptionDeclared", "OverlyBroadThrowsClause" })
public final class ThrowingFunctionTest
    extends ThrowingInterfaceBaseTest<ThrowingFunction<Type1, Type2>, Function<Type1, Type2>, Type2>
{
    private final Type1 arg = Type1.mock();
    private final Type2 ret1 = Type2.mock();
    private final Type2 ret2 = Type2.mock();

    @Override
    protected ThrowingFunction<Type1, Type2> getBaseInstance()
    {
        return SpiedThrowingFunction.newSpy();
    }

    @Override
    protected ThrowingFunction<Type1, Type2> getPreparedInstance()
        throws Throwable
    {
        final ThrowingFunction<Type1, Type2> spy = getBaseInstance();

        when(spy.doApply(arg)).thenReturn(ret1).thenThrow(checked)
            .thenThrow(unchecked).thenThrow(error);

        return spy;
    }

    @Override
    protected Function<Type1, Type2> getNonThrowingInstance()
    {
        //noinspection unchecked
        return mock(Function.class);
    }

    @Override
    protected Runnable runnableFrom(final Function<Type1, Type2> instance)
    {
        return () -> instance.apply(arg);
    }

    @Override
    protected Callable<Type2> callableFrom(
        final Function<Type1, Type2> instance)
    {
        return () -> instance.apply(arg);
    }

    @Override
    public void testUnchained()
        throws Throwable
    {
        final ThrowingFunction<Type1, Type2> instance = getPreparedInstance();

        final Runnable runnable = runnableFrom(instance);
        final Callable<Type2> callable = callableFrom(instance);

        assertThat(callable.call()).isSameAs(ret1);

        verifyCheckedRethrow(runnable, ThrownByLambdaException.class);

        verifyUncheckedThrow(runnable);

        verifyErrorThrow(runnable);
    }

    @Override
    public void testChainedWithOrThrow()
        throws Throwable
    {
        final Function<Type1, Type2> instance
            = getPreparedInstance().orThrow(MyException.class);

        final Runnable runnable = runnableFrom(instance);
        final Callable<Type2> callable = callableFrom(instance);

        assertThat(callable.call()).isSameAs(ret1);

        verifyCheckedRethrow(runnable, MyException.class);

        verifyUncheckedThrow(runnable);

        verifyErrorThrow(runnable);
    }

    @Override
    public void testChainedWithOrTryWith()
        throws Throwable
    {
        final ThrowingFunction<Type1, Type2> first = getPreparedInstance();
        final ThrowingFunction<Type1, Type2> second = getBaseInstance();
        when(second.doApply(arg)).thenReturn(ret2);

        final Function<Type1, Type2> instance = first.orTryWith(second);

        final Runnable runnable = runnableFrom(instance);
        final Callable<Type2> callable = callableFrom(instance);

        assertThat(callable.call()).isSameAs(ret1);

        assertThat(callable.call()).isSameAs(ret2);

        verifyUncheckedThrow(runnable);

        verifyErrorThrow(runnable);
    }

    @Override
    public void testChainedWithOr()
        throws Throwable
    {
        final ThrowingFunction<Type1, Type2> first = getPreparedInstance();
        final Function<Type1, Type2> second = getNonThrowingInstance();
        when(second.apply(arg)).thenReturn(ret2);

        final Function<Type1, Type2> instance = first.or(second);

        final Runnable runnable = runnableFrom(instance);
        final Callable<Type2> callable = callableFrom(instance);

        assertThat(callable.call()).isSameAs(ret1);
        assertThat(callable.call()).isSameAs(ret2);

        verifyUncheckedThrow(runnable);

        verifyErrorThrow(runnable);
    }

    public void testChainedWithOrReturn()
        throws Throwable
    {
        final ThrowingFunction<Type1, Type2> first = getPreparedInstance();

        final Function<Type1, Type2> instance = first.orReturn(ret2);

        final Runnable runnable = runnableFrom(instance);
        final Callable<Type2> callable = callableFrom(instance);

        assertThat(callable.call()).isSameAs(ret1);
        assertThat(callable.call()).isSameAs(ret2);

        verifyUncheckedThrow(runnable);

        verifyErrorThrow(runnable);
    }
}