package com.krb.component_base.helper;

import android.content.Context;

import com.krb.kprogresshud.KProgressHUD;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;

/**
 * Created by yinbing on 2018/8/2 0002.
 */
public class HUDFactory {
    private static HUDFactory factory;
    private WeakReference<KProgressHUD> weakReference;

    private HUDFactory() {
    }

    public static HUDFactory getInstance() {
        if (factory == null) {
            synchronized (HUDFactory.class) {
                if (factory == null) {
                    factory = new HUDFactory();
                }
            }
        }
        return factory;
    }

    public KProgressHUD creatHUD(Context mContext) {
        KProgressHUD hud;
        if (weakReference != null && weakReference.get() != null && weakReference.get().isShowing()) {
            hud = weakReference.get();
            Class hudClazz = hud.getClass();
            try {
                Field field = hudClazz.getDeclaredField("mContext");
                field.setAccessible(true);
                Context fContext = (Context) field.get(hud);
                if (fContext == mContext) {
                    return hud;
                }
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else {
            hud = new KProgressHUD(mContext);
            weakReference = new WeakReference<>(hud);
        }
        return hud;
    }
}
