import * as style from "./style/Inventory";
import ItemBox from "@/components/common/Itembox/ItemBox";
import { useState } from "react";

type ItemType = {
  name: string;
  imgSrc: string;
};

const typeList: ItemType[] = [
  {
    name: "HEAD",
    imgSrc: "/InventoryType/head.png",
  },
  {
    name: "BODY",
    imgSrc: "/InventoryType/body.png",
  },
  {
    name: "EFFECT",
    imgSrc: "/InventoryType/effect.png",
  },
];

const Inventory = (props: any) => {
  const handleItemClick = (gifUrl: string) => {
    // 클릭한 아이템의 gifSrc 값을 상위 컴포넌트로 전달
    props.onItemClick(gifUrl);
  };

  const handleTypeClick = (typeName: string) => {
    setItemType(typeName);
  };

  const [itemType, setItemType] = useState("HEAD");

  return (
    <style.Inventory>
      <style.InventoryBar>
        {typeList.map((type, index) => (
          <style.InventoryType
            key={index}
            onClick={() => handleTypeClick(type.name)}
            selected={itemType === type.name}
          >
            <style.InventoryTypeImg
              src={type.imgSrc}
              alt={type.name}
            />
          </style.InventoryType>
        ))}
      </style.InventoryBar>
      {itemType === "HEAD" && (
        <style.InventoryBox>
          {props.headItemList.map(
            (item: object, index: number) => (
              <ItemBox
                item={item}
                key={index}
                onItemClick={handleItemClick}
              />
            )
          )}
        </style.InventoryBox>
      )}
      {itemType === "BODY" && (
        <style.InventoryBox>
          {props.bodyItemList.map(
            (item: object, index: number) => (
              <ItemBox
                item={item}
                key={index}
                onItemClick={handleItemClick}
              />
            )
          )}
        </style.InventoryBox>
      )}
      {itemType === "EFFECT" && (
        <style.InventoryBox>
          {props.effectItemList.map(
            (item: object, index: number) => (
              <ItemBox
                item={item}
                key={index}
                onItemClick={handleItemClick}
              />
            )
          )}
        </style.InventoryBox>
      )}
    </style.Inventory>
  );
};

export default Inventory;
