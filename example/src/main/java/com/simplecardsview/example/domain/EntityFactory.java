package com.simplecardsview.example.domain;

import com.simplecardsview.model.Entity;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by artur on 03/09/14.
 */

public class EntityFactory implements Entity {

    public static Entity getInstance(Object claz) {

        try{
            if (claz.equals(MyEntity.class)) {
                return MyEntity.class.newInstance();
            }
        } catch (java.lang.InstantiationException e) {
            Logger.getLogger(EntityFactory.class.getName()).log(Level.WARNING,"Não foi possível " +
                    "instanciar objeto do tipo Entity " + claz.getClass().getName(), e);
        } catch (IllegalAccessException e) {
            Logger.getLogger(EntityFactory.class.getName()).log(Level.WARNING,"Não foi possível " +
                    "instanciar objeto do tipo Entity " + claz.getClass().getName(), e);
        }

        return null;
    }

}
