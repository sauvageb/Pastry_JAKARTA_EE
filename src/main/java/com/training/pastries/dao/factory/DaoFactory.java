package com.training.pastries.dao.factory;

import com.training.pastries.dao.base.BaseDao;
import com.training.pastries.dao.PastryDao;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public final class DaoFactory {

    private Map<Class<? extends BaseDao>, BaseDao> daos = new HashMap<>();
    private static DaoFactory instance;

    private DaoFactory() {
    }

    public static PastryDao getPastryDao() {
        return getInstance().getDao(PastryDao.class);
    }

    public static DaoFactory getInstance() {
        if (instance == null) {
            instance = new DaoFactory();
        }
        return instance;
    }

    public <T extends BaseDao> T getDao(Class<T> daoClazz) {
        T dao = daoClazz.cast(daos.get(daoClazz));
        if (dao != null) {
            return dao;
        }

        try {
            dao = (T) daoClazz.getConstructor().newInstance();
            daos.put(daoClazz, dao);
            return dao;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }


}
