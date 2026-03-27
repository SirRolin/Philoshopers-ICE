package philosophers_ice;

import java.util.ArrayList;

public class Accessory extends Item{
    /**
	 * 
	 */
	private static final long serialVersionUID = 101L;

	public Accessory(Accessory accessory){
        name = accessory.name;
    }
}
