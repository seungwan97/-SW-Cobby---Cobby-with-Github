import * as style from "./style/Inventory";
import ItemBox from "@/components/common/Itembox/ItemBox";

type ItemType = {
  name: string;
  imgSrc: string;
};

const typeList: ItemType[] = [
  {
    name: "Head",
    imgSrc: "/navItems/cobbyface.png",
  },
  {
    name: "Body",
    imgSrc: "/navItems/cobbyface.png",
  },
  {
    name: "Effect",
    imgSrc: "/navItems/cobbyface.png",
  },
];

const Inventory = () => {
  return (
    <style.Inventory>
      여기는 인벤토리야
      <style.InventoryBar>
        {typeList.map((type, index) => (
          <style.InventoryType key={index}>
            <style.InventoryTypeImg
              src={type.imgSrc}
              alt={type.name}
            />
          </style.InventoryType>
        ))}
      </style.InventoryBar>
      <style.InventoryBox>
        <ItemBox />
        <ItemBox />
        <ItemBox />
        <ItemBox />
        <ItemBox />
        <ItemBox />
        <ItemBox />
        <ItemBox />
        <ItemBox />
        <ItemBox />
        <ItemBox />
        <ItemBox />
      </style.InventoryBox>
    </style.Inventory>
  );
};

export default Inventory;
