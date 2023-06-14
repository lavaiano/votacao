package com.votacao.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.management.ReflectionException;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class ReflectionUtils {

	private static final String GET = "get";
	private static final String SET = "set";

	private ReflectionUtils() {
		super();
	}

	public static ParameterizedType getParameterizedType(final Class<?> clazz) {
		if (clazz.equals(Object.class)) {
			return null;
		}
		if (clazz.getGenericSuperclass() instanceof ParameterizedType) {
			return (ParameterizedType) clazz.getGenericSuperclass();
		}

		return getParameterizedType(clazz.getSuperclass());
	}

	public static <T> Method getGetterMethod(final Class<T> clazz, final String fieldName) {
		return getMethod(clazz, GET, fieldName);
	}

	public static Object executeGetterMethod(final Object object, final String fieldName) throws ReflectionException {
		try {
			final Field field = getField(object, fieldName);
			if (field != null) {
				final Class valueClass = field.getType();
				if (valueClass != null && List.class.isAssignableFrom(valueClass)) {
					field.setAccessible(true);
					return field.get(object);
				}
			}
		} catch (final Exception e) {
			throw new ReflectionException(e);
		}

		return executeMethod(GET, object, fieldName, new Class[] {}, new Object[] {});
	}

	public static <T> Method getSetterMethod(final Class<T> clazz, final String fieldName, final Class... params) {
		return getMethod(clazz, SET, fieldName, params);
	}

	public static <T> Object executeSetterMethod(final Object object, final String fieldName, final T value)
			throws ReflectionException {
		final Class<T> valueClass = getFieldType(object, fieldName);
		if (valueClass != null && List.class.isAssignableFrom(valueClass)) {

			try {
				final Field field = getField(object, fieldName);
				if (field != null) {
					field.setAccessible(true);
					field.set(object, value);
				}
			} catch (final Exception e) {
				throw new ReflectionException(e);
			}

			return null;
		}

		return executeMethod(SET, object, fieldName, new Class[] { valueClass }, new Object[] { value });
	}

	public static <T> Object setValueInField(final Object object, final Field field, final T value)
			throws ReflectionException {
		try {
			if (field != null) {
				field.setAccessible(true);
				field.set(object, value);
			}
		} catch (final Exception e) {
			throw new ReflectionException(e);
		}

		return null;
	}

	public static Class getFieldType(Object object, String fieldName) throws ReflectionException {
		object = getLastObject(object, fieldName);
		final int idx = fieldName.lastIndexOf('.');
		if (idx > 0) {
			fieldName = fieldName.substring(idx + 1);
		}
		return getFieldType(object.getClass(), fieldName);
	}

	public static Object getLastObject(Object obj, String fieldName) throws ReflectionException {
		if (fieldName.indexOf('.') >= 0) {
			final String fieldNameTmp = fieldName.substring(fieldName.indexOf('.') + 1);
			fieldName = fieldName.substring(0, fieldName.indexOf('.'));

			Object tmp = executeGetterMethod(obj, fieldName);
			if (tmp != null && Collection.class.isAssignableFrom(tmp.getClass())) {
				final Field field = getField(obj, fieldName);
				final Class clazz = (Class) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];
				try {
					tmp = clazz.newInstance();
				} catch (final Exception e) {
					throw new ReflectionException(e);
				}
			} else if (tmp == null) {
				tmp = newInstance(getFieldType(obj.getClass(), fieldName));
				executeSetterMethod(obj, fieldName, tmp);
			}
			obj = tmp;
			return getLastObject(obj, fieldNameTmp);
		}

		return obj;
	}

	public static <T> T newInstance(final Class<T> clazz, final Object... params) throws ReflectionException {
		if (clazz != null) {
			final ArrayList<Class> classes = new ArrayList<>();
			for (final Object param : params) {
				if (param != null && !param.toString().equals("")) {
					classes.add(param.getClass());
				}
			}
			Constructor<T> cons = null;
			try {
				if (classes.isEmpty()) {
					final Class[] classArray = new Class[classes.size()];
					cons = clazz.getDeclaredConstructor(classes.toArray(classArray));
					return cons != null ? cons.newInstance(params) : null;
				} else {
					return clazz.newInstance();
				}
			} catch (final Exception e) {
				throw new ReflectionException(e);
			}
		}

		return null;
	}

	public static Field getField(Object object, String fieldName) throws ReflectionException {

		object = getLastObject(object, fieldName);
		final int idx = fieldName.lastIndexOf('.');

		if (idx > 0) {
			fieldName = fieldName.substring(idx + 1);
		}

		try {
			return object.getClass().getDeclaredField(fieldName);
		} catch (final Exception e) {
			throw new ReflectionException(e);
		}
	}

	public static Class getFieldType(final Class objectClass, final String fieldName) {
		Class ret = null;
		try {
			final Field f = objectClass.getDeclaredField(fieldName);
			ret = f.getType();
		} catch (final Exception e) {
			if (!objectClass.isInterface() && !Object.class.equals(objectClass)) {
				return getFieldType(objectClass.getSuperclass(), fieldName);
			}
		}

		return ret;
	}

	public static <T> Method getMethod(final Class<T> clazz, final String prefix, final String fieldName,
			final Class... params) {
		final String methodName = prefix != null
				? prefix + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1)
				: fieldName;
		Method m = null;
		try {
			m = clazz.getDeclaredMethod(methodName, params);
		} catch (final Exception e) {
			try {
				m = clazz.getMethod(methodName, params);
			} catch (final Exception e1) {
				final Method[] ms = clazz.getMethods();
				for (final Method mtmp : ms) {
					if (mtmp.getName().equals(methodName)) {
						m = mtmp;
						break;
					}
				}
			}
		}

		return m;
	}

	public static Object executeMethod(final String prefix, Object object, String fieldName, final Class[] valueClass,
			final Object[] value) throws ReflectionException {
		if (object == null) {
			return null;
		}

		Object ret = null;

		object = getLastObject(object, fieldName);
		if (object != null) {
			final Class objectClass = object.getClass();

			if (fieldName.indexOf('.') >= 0) {
				fieldName = fieldName.substring(fieldName.lastIndexOf('.') + 1);
			}

			final Method method = getMethod(objectClass, prefix, fieldName, valueClass);
			if (method != null) {
				try {
					ret = method.invoke(object, value);
				} catch (final Exception e) {
					throw new ReflectionException(e);
				}
			}
		}

		return ret;
	}

	public static final List<Field> getFieldList(final Class<?> clazz) {
		return getFieldList(clazz, Object.class);
	}

	public static final List<Field> getFieldList(final Class<?> clazz, Class<?> clazzLimite) {
		if (clazzLimite == null || !clazzLimite.isAssignableFrom(clazz)) {
			clazzLimite = clazz.getSuperclass();
		}

		Class parentClass = clazz;
		final ArrayList<Field> fieldList = new ArrayList<>();
		while (true) {
			if (!clazzLimite.equals(parentClass)) {
				final Field[] parentFieldList = parentClass.getDeclaredFields();
				if (parentFieldList.length > 0) {
					fieldList.addAll(0, Arrays.asList(parentFieldList));
				}
			} else {
				break;
			}
			parentClass = parentClass.getSuperclass();
		}

		return fieldList;
	}
}