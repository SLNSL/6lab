package messenger;


import data.Product;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractMessenger implements Messenger, Serializable {

    protected Map<String, String> commandsInfo = new HashMap<>();

    @Override
    public String getCommandsInfo() {
        StringBuilder info = new StringBuilder();
        for (String i : commandsInfo.keySet()) {
            info.append(i + ": " + commandsInfo.get(i) + "\n");
        }
        return info.toString();
    }


    @Override
    public String getFieldsInfo(Integer key, Product product) {
        String info = "";
        info += "key: " + key + "\n";
        info += "    id: " + product.getId() + "\n";
        info += "    name: " + product.getName() + "\n";
        info += "    coordinates: " + "\n";
        info += "        x: " + product.getCoordinates().getX() + "\n";
        info += "        y: " + product.getCoordinates().getY() + "\n";
        info += "    creationDate: " + product.getCreationDate() + "\n";
        info += "    price: " + product.getPrice() + "\n";
        info += "    partNumber: " + product.getPartNumber() + "\n";
        info += "    manufactureCost: " + product.getManufactureCost() + "\n";
        info += "    unitOfMeasure: " + product.getUnitOfMeasure() + "\n";
        if (product.getOwner() == null) {
            info += "    owner: " + null + "\n";
        } else {
            info += "    owner: " + "\n";
            info += "        name: " + product.getOwner().getName() + "\n";
            info += "        passportID: " + product.getOwner().getPassportID() + "\n";
            info += "        hairColor: " + product.getOwner().getHairColor() + "\n";
            info += "        location: " + "\n";
            info += "            x: " + product.getOwner().getLocation().getX() + "\n";
            info += "            y: " + product.getOwner().getLocation().getY() + "\n";
            info += "            z: " + product.getOwner().getLocation().getZ() + "\n";
            info += "            name: " + product.getOwner().getLocation().getName() + "\n";
        }
        info += "\n";
        return info;

    }

}
