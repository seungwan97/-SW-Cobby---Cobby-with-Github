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

const Inventory = (props: any) => {
  const handleItemClick = (gifSrc: string) => {
    // 클릭한 아이템의 gifSrc 값을 상위 컴포넌트로 전달
    props.onItemClick(gifSrc);
    // console.log("Clicked item:", gifSrc);
  };

  return (
    <style.Inventory>
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
        {props.itemList.map(
          (item: object, index: Number) => (
            <ItemBox
              item={item}
              key={index}
              onItemClick={handleItemClick}
            />
          )
        )}
      </style.InventoryBox>
    </style.Inventory>
  );
};

export default Inventory;
