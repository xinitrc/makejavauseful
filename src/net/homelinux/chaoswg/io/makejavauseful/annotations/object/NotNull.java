package net.homelinux.chaoswg.io.makejavauseful.annotations.object;

import java.lang.annotation.Target;
import java.lang.annotation.Retention;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

@Target({FIELD, PARAMETER, LOCAL_VARIABLE, METHOD})
@Retention(RUNTIME)
public @interface NotNull {}