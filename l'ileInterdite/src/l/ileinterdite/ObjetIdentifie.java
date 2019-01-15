package l.ileinterdite;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 *
 * @author IUT2-Dept Info
 */
public class ObjetIdentifie {
    private static Integer lastId = 1 ;
    protected Integer id ;
    
    public ObjetIdentifie() {
       this.id = ObjetIdentifie.getNextId();
    } 

    public Integer getId() {
        return this.id ;
    }

    public static Integer getNextId() {
        return lastId++ ;
    }

    public void setId(Integer id) {
        this.id = id ;
    }
    
public ArrayList<Integer> listetoid(LinkedHashMap<Integer, ObjetIdentifie> liste){
    ArrayList<Integer> listeid = new ArrayList<>();
    for(Integer key : liste.keySet()){
        listeid.add(key);
    }
    return listeid;
}    
}
