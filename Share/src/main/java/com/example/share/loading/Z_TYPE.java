package com.example.share.loading;


/**
 * Created by zyao89 on 2017/3/19.
 * Contact me at 305161066@qq.com or zyao89@gmail.com
 * For more projects: https://github.com/zyao89
 * My Blog: http://zyao89.me
 */
public enum Z_TYPE
{

    ROTATE_CIRCLE(RotateCircleBuilder.class),
    ;

    private final Class<?> mBuilderClass;

    Z_TYPE(Class<?> builderClass)
    {
        this.mBuilderClass = builderClass;
    }

    public <T extends ZLoadingBuilder> T newInstance()
    {
        try
        {
            return (T) mBuilderClass.newInstance();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
