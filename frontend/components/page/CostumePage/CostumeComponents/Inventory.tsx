import * as style from "./style/Inventory";
import ItemBox from "@/components/common/Itembox/ItemBox";
import { useState, useEffect } from "react";

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
  const handleItemClick = (itemInfo: {}) => {
    // 클릭한 아이템의 정보를 상위 컴포넌트로 전달
    props.onItemClick(itemInfo);
  };

  const handleTypeClick = (typeName: string) => {
    setItemType(typeName);
  };

  const [itemType, setItemType] = useState("HEAD");
  const [headArr, setHeadArr] = useState([]);
  const [bodyArr, setBodyArr] = useState([]);
  const [effectArr, setEffectArr] = useState([]);

  useEffect(() => {
    const index0 = {
      costumeId: 0,
      name: "empty",
      category: itemType,
      questId: null,
      imgUrl: "/CostumeItems_IMG/empty.png",
      gifUrl: "/CostumeItems_GIF/empty.gif",
    };

    if (itemType === "HEAD") {
      const arr: any = [index0, ...props.headItemList];
      setHeadArr(arr);
    } else if (itemType === "BODY") {
      const arr: any = [index0, ...props.bodyItemList];
      setBodyArr(arr);
    } else {
      const arr: any = [index0, ...props.effectItemList];
      setEffectArr(arr);
    }
  }, [itemType]);

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
          {headArr.map((item: object, index: number) => (
            <ItemBox
              item={item}
              key={index}
              onItemClick={handleItemClick}
            />
          ))}
        </style.InventoryBox>
      )}
      {itemType === "BODY" && (
        <style.InventoryBox>
          {bodyArr.map((item: object, index: number) => (
            <ItemBox
              item={item}
              key={index}
              onItemClick={handleItemClick}
            />
          ))}
        </style.InventoryBox>
      )}
      {itemType === "EFFECT" && (
        <style.InventoryBox>
          {effectArr.map((item: object, index: number) => (
            <ItemBox
              item={item}
              key={index}
              onItemClick={handleItemClick}
            />
          ))}
        </style.InventoryBox>
      )}
    </style.Inventory>
  );
};

export default Inventory;
