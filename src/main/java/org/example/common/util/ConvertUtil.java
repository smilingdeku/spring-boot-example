package org.example.common.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.NumberFormat;
import java.text.ParseException;

/**
 * 数据转换工具类
 */
public class ConvertUtil {

    /**
     * 转换为 String
     *
     * @param obj 数据
     * @return String
     */
    public static String toStr(final Object obj) {
        if (obj != null) {
            return obj.toString();
        }
        return null;
    }

    /**
     * 转换为 String
     *
     * @param obj 数据
     * @param def 默认值
     * @return String
     */
    public static String toStr(final Object obj, String def) {
        String answer = toStr(obj);
        if (answer == null) {
            answer = def;
        }
        return answer;
    }

    /**
     * 转换为 Number
     *
     * @param obj 数据
     * @return Number
     */
    public static Number toNumber(final Object obj) {
        if (obj != null) {
            if (obj instanceof Number) {
                return (Number) obj;
            } else if (obj instanceof Boolean) {
                Boolean flag = (Boolean) obj;
                return flag ? 1 : 0;
            } else if (obj instanceof String) {
                try {
                    String text = (String) obj;
                    return NumberFormat.getInstance().parse(text);
                } catch (ParseException e) {
                    // ignore exception
                }
            }
        }
        return null;
    }

    /**
     * 转换为 Number
     *
     * @param obj 数据
     * @param def 默认值
     * @return Number
     */
    public static Number toNumber(final Object obj, Number def) {
        Number answer = toNumber(obj);
        if (answer == null) {
            answer = def;
        }
        return answer;
    }

    /**
     * 转换为 Boolean
     *
     * @param obj 数据
     * @return Boolean
     */
    public static Boolean toBoolean(final Object obj) {
        if (obj != null) {
            if (obj instanceof Boolean) {
                return (Boolean) obj;
            } else if (obj instanceof String) {
                return Boolean.valueOf((String) obj);
            } else if (obj instanceof Number) {
                Number n = (Number) obj;
                return (n.intValue() != 0) ? Boolean.TRUE : Boolean.FALSE;
            }
        }
        return null;
    }

    /**
     * 转换为 Boolean
     *
     * @param obj 数据
     * @param def 默认值
     * @return Boolean
     */
    public static Boolean toBoolean(final Object obj, Boolean def) {
        Boolean answer = toBoolean(obj);
        if (answer == null) {
            answer = def;
        }
        return answer;
    }

    /**
     * 转换为 boolean
     *
     * @param obj 数据
     * @return boolean
     */
    public static boolean toBooleanValue(final Object obj) {
        Boolean booleanObject = toBoolean(obj);
        if (booleanObject == null) {
            return false;
        }
        return booleanObject;
    }

    /**
     * 转化为 boolean
     *
     * @param obj 数据
     * @param def 默认值
     * @return boolean
     */
    public static boolean toBooleanValue(final Object obj, boolean def) {
        Boolean booleanObject = toBoolean(obj);
        if (booleanObject == null) {
            return def;
        }
        return booleanObject;
    }

    /**
     * 转换为 Byte
     *
     * @param obj 数据
     * @return Byte
     */
    public static Byte toByte(final Object obj) {
        Number answer = toNumber(obj);
        if (answer == null) {
            return null;
        } else if (answer instanceof Byte) {
            return (Byte) answer;
        }
        return answer.byteValue();
    }

    /**
     * 转换为 Byte
     *
     * @param obj 数据
     * @param def 默认值
     * @return Byte
     */
    public static Byte toByte(final Object obj, Byte def) {
        Byte answer = toByte(obj);
        if (answer == null) {
            answer = def;
        }
        return answer;
    }

    /**
     * 转换为 byte
     *
     * @param obj 数据
     * @return byte
     */
    public static byte toByteValue(final Object obj) {
        Byte byteObject = toByte(obj);
        if (byteObject == null) {
            return 0;
        }
        return byteObject;
    }

    /**
     * 转换为 byte
     *
     * @param obj 数据
     * @param def 默认值
     * @return byte
     */
    public static byte toByteValue(final Object obj, byte def) {
        Byte byteObject = toByte(obj);
        if (byteObject == null) {
            return def;
        }
        return byteObject;
    }

    /**
     * 转换为 Short
     *
     * @param obj 数据
     * @return Short
     */
    public static Short toShort(final Object obj) {
        Number answer = toNumber(obj);
        if (answer == null) {
            return null;
        } else if (answer instanceof Short) {
            return (Short) answer;
        }
        return answer.shortValue();
    }

    /**
     * 转换为 Short
     *
     * @param obj 数据
     * @param def 默认值
     * @return Short
     */
    public static Short toShort(final Object obj, Short def) {
        Short answer = toShort(obj);
        if (answer == null) {
            answer = def;
        }
        return answer;
    }

    /**
     * 转换为 short
     *
     * @param obj 数据
     * @return short
     */
    public static short toShortValue(final Object obj) {
        Short shortObject = toShort(obj);
        if (shortObject == null) {
            return 0;
        }
        return shortObject;
    }

    /**
     * 转换为 short
     *
     * @param obj 数据
     * @param def 默认值
     * @return short
     */
    public static short toShortValue(final Object obj, short def) {
        Short shortObject = toShort(obj);
        if (shortObject == null) {
            return def;
        }
        return shortObject;
    }

    /**
     * 转换为 Integer
     *
     * @param obj 数据
     * @return Integer
     */
    public static Integer toInteger(final Object obj) {
        Number answer = toNumber(obj);
        if (answer == null) {
            return null;
        } else if (answer instanceof Integer) {
            return (Integer) answer;
        }
        return answer.intValue();
    }

    /**
     * 转换为 Integer
     *
     * @param obj 数据
     * @param def 默认值
     * @return Integer
     */
    public static Integer toInteger(final Object obj, Integer def) {
        Integer answer = toInteger(obj);
        if (answer == null) {
            answer = def;
        }
        return answer;
    }

    /**
     * 转换为 int
     *
     * @param obj 数据
     * @return int
     */
    public static int toIntValue(final Object obj) {
        Integer integerObject = toInteger(obj);
        if (integerObject == null) {
            return 0;
        }
        return integerObject;
    }

    /**
     * 转换为 int
     *
     * @param obj 数据
     * @param def 默认值
     * @return int
     */
    public static int toIntValue(final Object obj, int def) {
        Integer integerObject = toInteger(obj);
        if (integerObject == null) {
            return def;
        }
        return integerObject;
    }

    /**
     * 转换为 Long
     *
     * @param obj 数据
     * @return Long
     */
    public static Long toLong(final Object obj) {
        Number answer = toNumber(obj);
        if (answer == null) {
            return null;
        } else if (answer instanceof Long) {
            return (Long) answer;
        }
        return answer.longValue();
    }

    /**
     * 转换为 Long
     *
     * @param obj 数据
     * @param def 默认值
     * @return Long
     */
    public static Long toLong(final Object obj, Long def) {
        Long answer = toLong(obj);
        if (answer == null) {
            answer = def;
        }
        return answer;
    }

    /**
     * 转换为 long
     *
     * @param obj 数据
     * @return long
     */
    public static long toLongValue(final Object obj) {
        Long longObject = toLong(obj);
        if (longObject == null) {
            return 0L;
        }
        return longObject;
    }

    /**
     * 转换为 long
     *
     * @param obj 数据
     * @param def 默认值
     * @return long
     */
    public static long toLongValue(final Object obj, long def) {
        Long longObject = toLong(obj);
        if (longObject == null) {
            return def;
        }
        return longObject;
    }

    /**
     * 转换为 Float
     *
     * @param obj 数据
     * @return Float
     */
    public static Float toFloat(final Object obj) {
        Number answer = toNumber(obj);
        if (answer == null) {
            return null;
        } else if (answer instanceof Float) {
            return (Float) answer;
        }
        return answer.floatValue();
    }

    /**
     * 转换为 Float
     *
     * @param obj 数据
     * @param def 默认值
     * @return Float
     */
    public static Float toFloat(final Object obj, Float def) {
        Float answer = toFloat(obj);
        if ( answer == null ) {
            answer = def;
        }
        return answer;
    }

    /**
     * 转换为 float
     *
     * @param obj 数据
     * @return float
     */
    public static float toFloatValue(final Object obj) {
        Float floatObject = toFloat(obj);
        if (floatObject == null) {
            return 0f;
        }
        return floatObject;
    }

    /**
     * 转换为 float
     *
     * @param obj 数据
     * @param def 默认值
     * @return float
     */
    public static float toFloatValue(final Object obj, float def) {
        Float floatObject = toFloat(obj);
        if (floatObject == null) {
            return def;
        }
        return floatObject;
    }

    /**
     * 转换为 Double
     *
     * @param obj 数据
     * @return Double
     */
    public static Double toDouble(final Object obj) {
        Number answer = toNumber(obj);
        if (answer == null) {
            return null;
        } else if (answer instanceof Double) {
            return (Double) answer;
        }
        return answer.doubleValue();
    }

    /**
     * 转换为 Double
     *
     * @param obj 数据
     * @param def 默认值
     * @return Double
     */
    public static Double toDouble(final Object obj, Double def) {
        Double answer = toDouble(obj);
        if ( answer == null ) {
            answer = def;
        }
        return answer;
    }

    /**
     * 转换为 double
     *
     * @param obj 数据
     * @return double
     */
    public static double toDoubleValue(final Object obj) {
        Double doubleObject = toDouble(obj);
        if (doubleObject == null) {
            return 0d;
        }
        return doubleObject;
    }

    /**
     * 转换为 double
     *
     * @param obj 数据
     * @param def 默认值
     * @return double
     */
    public static double toDoubleValue(final Object obj, double def) {
        Double doubleObject = toDouble(obj);
        if (doubleObject == null) {
            return def;
        }
        return doubleObject;
    }

    /**
     * 转换为 BigInteger
     *
     * @param obj 数据
     * @return BigInteger
     */
    public static BigInteger toBigInteger(final Object obj) {
        Long longObject = toLong(obj);
        if (longObject == null) {
            return BigInteger.ZERO;
        }
        return BigInteger.valueOf(longObject);
    }

    /**
     * 转换为 BigInteger
     *
     * @param obj 数据
     * @param def 默认值
     * @return BigInteger
     */
    public static BigInteger toBigInteger(final Object obj, BigInteger def) {
        Long longObject = toLong(obj);
        if (longObject == null) {
            return def;
        }
        return BigInteger.valueOf(longObject);
    }

    /**
     * 转换为 BigDecimal
     *
     * @param obj 数据
     * @return BigDecimal
     */
    public static BigDecimal toBigDecimal(final Object obj) {
        Double doubleObject = toDouble(obj);
        if (doubleObject == null) {
            return BigDecimal.ZERO;
        }
        return BigDecimal.valueOf(doubleObject);
    }

    /**
     * 转换为 BigDecimal
     *
     * @param obj 数据
     * @param def 默认值
     * @return BigDecimal
     */
    public static BigDecimal toBigDecimal(final Object obj, BigDecimal def) {
        Double doubleObject = toDouble(obj);
        if (doubleObject == null) {
            return def;
        }
        return BigDecimal.valueOf(doubleObject);
    }

    /**
     * 转换数据
     *
     * @param obj 数据
     * @param clz 目标类型类
     * @param <R> 目标
     * @return R
     */
    @SuppressWarnings("unchecked")
    public static <R> R cast(final Object obj, final Class<R> clz) {
        R result = null;
        if (Boolean.class.equals(clz) || boolean.class.equals(clz)) {
            result = (R) toBoolean(obj);
        } else if (Byte.class.equals(clz) || byte.class.equals(clz)) {
            result = (R) toByte(obj);
        } else if (Short.class.equals(clz) || short.class.equals(clz)) {
            result = (R) toShort(obj);
        } else if (Integer.class.equals(clz) || int.class.equals(clz)) {
            result = (R) toInteger(obj);
        } else if (Long.class.equals(clz) || long.class.equals(clz)) {
            result = (R) toLong(obj);
        } else if (Float.class.equals(clz) || float.class.equals(clz)) {
            result = (R) toFloat(obj);
        } else if (Double.class.equals(clz) || double.class.equals(clz)) {
            result = (R) toDouble(obj);
        } else if (BigInteger.class.equals(clz)) {
            result = (R) toBigInteger(obj);
        } else if (BigDecimal.class.equals(clz)) {
            result = (R) toBigDecimal(obj);
        } else if (String.class.equals(clz)) {
            result = (R) toStr(obj);
        }
        return result;
    }

    /**
     * 转换数据
     *
     * @param obj 数据
     * @param def 目标类型默认值
     * @param <R> 目标
     * @return R
     */
    @SuppressWarnings("unchecked")
    public static <R> R cast(final Object obj, R def) {
        R result = null;
        if (def == null) { return null; }
        Class<?> clz = def.getClass();
        if (Boolean.class.equals(clz)) {
            result = (R) toBoolean(obj, (Boolean) def);
        } else if (Byte.class.equals(clz)) {
            result = (R) toByte(obj, (Byte) def);
        } else if (Short.class.equals(clz)) {
            result = (R) toShort(obj, (Short) def);
        } else if (Integer.class.equals(clz)) {
            result = (R) toInteger(obj, (Integer) def);
        } else if (Long.class.equals(clz)) {
            result = (R) toLong(obj, (Long) def);
        } else if (Float.class.equals(clz)) {
            result = (R) toFloat(obj, (Float) def);
        } else if (Double.class.equals(clz)) {
            result = (R) toDouble(obj, (Double) def);
        } else if (BigInteger.class.equals(clz)) {
            result = (R) toBigInteger(obj, (BigInteger) def);
        } else if (BigDecimal.class.equals(clz)) {
            result = (R) toBigDecimal(obj, (BigDecimal) def);
        } else if (String.class.equals(clz)) {
            result = (R) toStr(obj, (String) def);
        }
        return result;
    }
    
}