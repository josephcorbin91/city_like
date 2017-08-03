package com.android.jco.citylike_android.activities.Activities.models;

import android.content.Context;
import android.location.Location;
import android.util.Log;

import java.io.File;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import android.content.Context;
import android.location.Location;

import com.mykeys.R;
import com.mykeys.activities.ICardViewIconItem;
import com.mykeys.state.KeyCreatedState;
import com.mykeys.state.KeyState;
import com.mykeys.util.IsRunningTest;
import android.util.Log;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

import java.io.File;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Created by jco on 8/3/2017.
 */

public class SeattleBuildingPermit {
}



public class Key extends DataSupport implements Serializable, ICardViewIconItem {


    @Column(nullable = false)
    private Float roll;

    @Column(nullable = false)
    private Float pitch;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String icon;
    @Column(nullable = false)
    private Integer iconTypeId;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private long keyring_id;

    @Column(nullable = true, defaultValue = "false")
    private Boolean lock_by_admin;


    @Column(nullable = false)
    private String localPath;

    @Column(nullable = true)
    private String error_message;


    @Column(nullable =true)
    private Integer certificateNumber;

    @Column(nullable = true)
    private boolean hasCertificateNumber;

    @Column(nullable =true)
    private Integer certificateNumberPassword;

    @Column(nullable = true)
    private String uuid;

    @Column(nullable = false, defaultValue = "false")
    private Boolean matched;

    @Column(nullable = false, defaultValue = "0")
    private Integer nbMatch;

    @Column(nullable = false, defaultValue = "regular")
    private String keyType;

    /**
     * No args constructor for use in serialization
     */

    public Key() {
        this.state = KeyCreatedState.class.getName();
        this.iconTypeId = R.drawable.ic_key;
        this.icon = "ic_key";
        this.roll = 0.0f;
        this.pitch = 0.0f;
        this.localPath = "";
        this.matched = null;
        this.nbMatch = 0;
        this.lock_by_admin = false;
        this.keyType = "regular";

    }

    public Key(Integer certificateNumber,Integer certificateNumberPassword,Boolean hasCertificateNumber, long keyring_id, String name, Float roll, Float pitch,
               String icon_id, String color, String localPath, String keyType) {
        this.iconTypeId = R.drawable.ic_key;
        this.lock_by_admin = false;
        this.certificateNumber = certificateNumber;
        this.certificateNumberPassword = certificateNumberPassword;
        this.hasCertificateNumber = hasCertificateNumber;


        this.keyring_id = keyring_id;
        this.name = name;
        this.roll = roll;
        this.pitch = pitch;
        this.icon = icon_id;
        this.color = color;
        this.state = KeyCreatedState.class.getName();
        this.localPath = localPath;
        this.matched = false;
        this.nbMatch = 0;
    }
    public Key(long keyring_id, String name, Float roll, Float pitch,
               String icon_id, String color, String localPath, String keyType) {
        this.certificateNumber = null;
        this.certificateNumberPassword = null;
        this.iconTypeId = R.drawable.ic_key;
        this.lock_by_admin = false;
        this.keyring_id = keyring_id;
        this.name = name;
        this.roll = roll;
        this.pitch = pitch;
        this.icon = icon_id;
        this.color = color;
        this.state = KeyCreatedState.class.getName();
        this.localPath = localPath;
        this.matched = false;
        this.nbMatch = 0;
    }

    public Key(long keyring_id, String name, Float roll, Float pitch,
               String icon_id, String color, String localPath, Integer iconTypeId, String keyType) {
        this.iconTypeId = iconTypeId;
        this.keyring_id = keyring_id;
        this.name = name;
        this.roll = roll;
        this.lock_by_admin = false;

        this.pitch = pitch;
        this.icon = icon_id;
        this.color = color;
        this.state = KeyCreatedState.class.getName();
        this.localPath = localPath;
    }

    public String keyType() {
        return keyType;
    }

    public void keyType(String keyType) {
        this.keyType = keyType;
    }

    public static Key cloneKey(Key key) {

        Key clonedKey = new Key(key.keyring_id, key.name, key.roll(), key.pitch(), key.icon(), key.color(), key.localPath(),key.keyType());
        return clonedKey;
    }

    //Used in keyring detail fragment to put keyrings into a ArrayList<Key>
    public static Key transformToKeyring(Key key, String color, String icon_id, String name) {
        Key clonedKey = new Key(key.keyring_id, name, key.roll(), key.pitch(), icon_id, color, key.localPath(),key.keyType());
        return clonedKey;
    }


    public long keyring_id() {
        return keyring_id;
    }
    public void keyring_id(long keyring_id) {
        this.keyring_id = keyring_id;
    }
    public void keyring_id(String keyring_id) {
        this.keyring_id = Long.valueOf(keyring_id);
    }

    /**
     * @return The id
     */
    public long getId() {
        return getBaseObjId();
    }



    /**
     * @return The roll
     */
    public Float roll() {
        return roll;
    }

    /**
     * @param roll The roll
     */
    public void roll(Float roll) {
        this.roll = roll;
    }

    /**
     * @return The pitch
     */
    public Float pitch() {
        return pitch;
    }

    /**
     * @return if key hasCertificateNumber
     * /
     */
    public Boolean hasCertificateNumber(){
        return this.hasCertificateNumber;
    }
    /**
     * @param pitch The pitch
     */
    public void pitch(Float pitch) {
        this.pitch = pitch;
    }

    /**
     * @return The state
     */
    public String state() {
        return state;
    }

    /**
     * @param state The state
     */
    public void state(String state) {
        this.state = state;
    }

    /**
     *
     * @return
     * The createdAt
     */
    // public String getCreatedAt() {
    //     return createdAt;
    //  }

    /**
     *
     * @param createdAt
     * The created_at
     */
    //  public void setCreatedAt(String createdAt) {
    //      this.createdAt = createdAt;
    //  }

    /**
     * @return The updatedAt
     */
//    public String getUpdatedAt() {
    //       return updatedAt;
    //   }
    public String uuid() {
        return uuid;
    }

    public void uuid(String uuid) {
        this.uuid = uuid;
    }

    public String getErrorMessage() {
        return error_message;
    }

    public void setErrorMessage(String message) {
        this.error_message = message;
    }

    public String localPath() {
        return localPath;
    }
    public void localPath(String localPath) {
        this.localPath = localPath;
    }

    /**
     * @return The name
     */
    public String name() {
        return this.name;
    }

    public void name(String name) {
        this.name = name;
    }

    public String icon() {
        return this.icon;
    }

    public void icon(String icon) {
        this.icon = icon;
    }

    public String color() {
        return this.color;
    }

    public void color(String color) {
        this.color = color;
    }

    public List<Lock> getLocks() {
        List<Lock> locks;
        try {
            locks = KeyLock.getLocks(this.getBaseObjId());
        } catch (Exception ex) {
            locks = new ArrayList<Lock>();
        }
        return locks;
    }

    public Lock addLock(String name, Location location) {
        Lock lock = null;
        KeyLock keyLock = null;
        if (name.length() > 0) {
            lock = new Lock(name, location);
            lock.saveThrows();
            keyLock = new KeyLock(this.getId(), lock.getId());
            keyLock.saveThrows();
        }
        return lock;
    }

    public Lock addLock(Lock mlock) {
        KeyLock keyLock = new KeyLock(this.getId(), mlock.getId());
        keyLock.saveThrows();
        return mlock;
    }

    public Keyring getKeyring() {
        return DataSupport.find(Keyring.class, this.keyring_id);
    }

    public List<Key> getMatchKeys(boolean remove_self) {

        Set<Long> keys = new LinkedHashSet<>();
        Set<Long> keys2 = new LinkedHashSet<>();
        Long id = this.getId();
        List<KeyDistance> keyDistances;
        Integer nbKey = 0;
        keys.add(this.getId());

        try {
            while (nbKey != keys.size()) {
                nbKey = keys.size();
                for (Long key_id : keys) {
                    keyDistances = DataSupport.where("(key1 = ? or key2 = ?) and match = 1", String.valueOf(key_id), String.valueOf(key_id)).select("Key1,key2").find(KeyDistance.class);
                    for (KeyDistance keydistance : keyDistances) {
                        keys.add(keydistance.getKey1Long());
                        keys.add(keydistance.getKey2Long());
                    }
                }
            }
        } catch (Exception ex) {
            keyDistances = new ArrayList<KeyDistance>();
        }

        if (remove_self)
            keys.remove(id);

        int i = 0;
        long[] longArray = new long[keys.size()];
        Iterator<Long> iterator = keys.iterator();

        while (iterator.hasNext()) {
            longArray[i++] = iterator.next();
        }
        List<Key> out_keys = new ArrayList<Key>();
        try {
            if (longArray.length > 0)
                out_keys = DataSupport.findAll(Key.class, longArray);
        } catch (Exception ex) {
            out_keys = new ArrayList<Key>();
        }
        return out_keys;
    }

    public int getBadge(Context context) {
        int badge = R.drawable.badge_black;
        try {
            badge = getStateInstance(context).getBadge();
        } catch (Exception ex) {
            setErrorMessage(ex.getMessage());
            save();
            ex.printStackTrace();
            Log.d("Key",ex.getMessage());
        }
        return badge;
    }

    // this method should always be called from background task
    public boolean synchronize(Context context) {
        try {
            return getStateInstance(context).synchronize();
        } catch (Exception ex) {
            setErrorMessage(ex.getMessage());
            save();
            ex.printStackTrace();
            Log.d("Key",ex.getMessage());
        }
        return true;
    }

    public void nextState(Context context) {
        try {
            getStateInstance(context).nextState();
        } catch (Exception ex) {
            setErrorMessage(ex.getMessage());
            save();
            ex.printStackTrace();
            Log.d("Key",ex.getMessage());
        }
    }

    public ArrayList<Profile> getProfile() {
        return (ArrayList<Profile>) DataSupport.where("key = ?", String.valueOf(this.getId())).find(Profile.class);
    }

    public ArrayList<Extrema> getExtrema() {
        return (ArrayList<Extrema>) DataSupport.where("key = ?", String.valueOf(this.getId())).find(Extrema.class);
    }

    public Integer getCertificateNumberPassword(){
        return this.certificateNumberPassword;
    }

    public Integer getCertificateNumber(){
        return this.certificateNumber;
    }
    public KeyState getStateInstance(Context context) throws Exception {
        User user = DataSupport.findLast(User.class);
        Constructor c = Class.forName(state()).getConstructor(Context.class, User.class, Key.class);
        return (KeyState) c.newInstance(context, user, this);

    }

    public void setMatched(boolean matched) {
        this.matched = matched;
    }

    public void setNbMatch(Integer nbMatch) {
        this.nbMatch = nbMatch;
    }

    public Integer getNbMatch() {
        return nbMatch;
    }

    @Override
    public String getItemTypeIcon() {
        if (this.iconTypeId.equals(R.drawable.ic_keyring_index))
            return "ic_keyring_index";
        if (this.iconTypeId.equals(R.drawable.lock_icon))
            return "lock_icon";
        if (this.name().equals("Fake Key"))
            return "Fake Key";

        if(this.name().equals(""))
            return "0";

        return "ic_key";
    }


    public String getItemBadgeColor(Context context) {
        try {
            return getStateInstance(context).getBadgeColor();
        } catch (Exception e) {
            e.printStackTrace();
            error_message = e.getMessage();
            save();
        }
        return "#FF0000";
    }

    @Override
    public String getItemBadgeText() {
        if (this.nbMatch == null || this.name().equals(""))
            return "";
        return this.nbMatch.toString();
    }

    @Override
    public String getItemBadgeTextColor(Context context) {
        try {
            return getStateInstance(context).getBadgeTextColor();
        } catch (Exception e) {
            e.printStackTrace();
            error_message = e.getMessage();
            save();
        }
        return "#FF0000";
    }

    @Override
    public String getText() {
        if (this.name().equals("Fake Key"))
            return "";
        else if (this.name().equals("DELETE"))
            return "Delete";
        else if(this.name().equals(""))
            return "";
        else if (this.name().contains("Keyring: "))
            return this.name();
        return this.name();
    }

    public int hashCode()
    {
        return this.name().hashCode()+this.icon.hashCode()+this.color.hashCode();
    }

    public boolean equals(Object other) {
        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof Key)) return false;
        Key otherKey = (Key) other;
        return this.name().equals(otherKey.name());
    }

    public void safeDelete() {
        this.deleteLocks();
        this.deleteProfile();
        this.deleteExtrema();
        this.deleteDistance();
        this.deleteImages();
        this.getKeyring().removeKey();
        this.delete();
    }

    private void deleteImages() {
        File file = new File(this.localPath() + ".small.png");
        if (file.exists() && !IsRunningTest.isRunningTest())
            file.delete();

        file = new File(this.localPath());
        if (file.exists() && !IsRunningTest.isRunningTest())
            file.delete();
    }

    public void deleteLocks() {
        List<Lock> locks = this.getLocks();
        List<KeyLock> keyLocks = DataSupport.where("key_id = ?", String.valueOf(this.getId())).find(KeyLock.class);

        // delete relation betweek key an lock
        for (KeyLock keyLock : keyLocks) {
            keyLock.delete();
        }
    }

    public void deleteLock(Lock lock) {
        List<KeyLock> keyLocks = DataSupport.where("key_id = ? and lock_id = ?", String.valueOf(this.getId()), String.valueOf(lock.getId())).find(KeyLock.class);

        // delete relation betweek key an lock
        for (KeyLock keyLock : keyLocks) {
            System.out.println("Names "+keyLock.getKey().name());
            keyLock.delete();
        }
        for(Key key : DataSupport.findAll(Key.class)){
            System.out.println("NAME"+ key.name());
        }
    }



    public void lock_by_admin(String lock) {
        this.lock_by_admin = Boolean.valueOf(lock);
    }
    public void lock_by_admin(boolean lock) {
        this.lock_by_admin = lock;
    }

    public boolean lock_by_admin() {
        return this.lock_by_admin;
    }

    private void deleteProfile() {
        String condition = "key = " + String.valueOf(this.getId());
        DataSupport.deleteAll(Profile.class, condition);
    }

    private void deleteExtrema() {
        String condition = "key = " + String.valueOf(this.getId());
        DataSupport.deleteAll(Extrema.class, condition);
    }

    private void deleteDistance() {
        String condition = "key1 = " + String.valueOf(this.getId()) + " or Key2=" + String.valueOf(this.getId());
        DataSupport.deleteAll(KeyDistance.class, condition);
    }

    @Override
    public synchronized boolean save() {
        return super.save();
    }

    public static Key by_uuid(String uuid) {
        return DataSupport.where("uuid = ?", uuid).findLast(Key.class);
    }


    public static void create(Map<String, String> data) throws InvocationTargetException, IllegalAccessException {
        Key key = new Key();
        MyModel<Key> myModel = new MyModel<Key>();
        key = myModel.update_fields(key, data);
        key.saveThrows();
    }

    public static void update(Map<String, String> data) throws InvocationTargetException, IllegalAccessException {
        Key key = Key.by_uuid(data.get("uuid"));
        MyModel<Key> myModel = new MyModel<Key>();
        key = myModel.update_fields(key, data);
        key.saveThrows();
    }
    public static void delete(Map<String, String> data) throws InvocationTargetException, IllegalAccessException {
        Key key = Key.by_uuid(data.get("uuid"));
        if(key != null)
            key.safeDelete();
    }
}
