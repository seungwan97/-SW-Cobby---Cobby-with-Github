import * as style from "./style/Inventory";
import ItemBox from "@/components/common/Itembox/ItemBox";
import { useState, useEffect } from "react";
import { getMyCostumes } from "@/pages/api/main";
import cookie from "react-cookies";

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
    console.log("CostumePage 컴포넌트로 ", itemInfo, " 전달했습니다.");
  };

  const handleTypeClick = (typeName: string) => {
    setItemType(typeName);
  };

  const [itemType, setItemType] = useState("HEAD");
  const [headArr, setHeadArr] = useState([]);
  const [bodyArr, setBodyArr] = useState([]);
  const [effectArr, setEffectArr] = useState([]);

  const [myHeadItems, setMyHeadItems] = useState([]);
  const [myBodyItems, setMyBodyItems] = useState([]);
  const [myEffectItems, setMyEffectItems] = useState([]);

  const [myHead, setMyHead] = useState({});
  const [myBody, setMyBody] = useState({});
  const [myEffect, setMyEffect] = useState({});

  useEffect(() => {
    const index0 = {
      costumeId: 0,
      name: "empty",
      category: itemType,
      questId: null,
      imgUrl: "/empty.png",
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

  // 사용자가 보유한 아이템 조회하자
  useEffect(() => {
    const token = cookie.load("Authorization");

    const getMyItems = async () => {
      // 내가 보유한 HEAD 코스튬 목록 불러오기
      const resMyHEAD = await getMyCostumes(token, "HEAD");
      // 내가 보유한 BODY 코스튬 목록 불러오기
      const resMyBODY = await getMyCostumes(token, "BODY");
      // 내가 보유한 EFFECT 코스튬 목록 불러오기
      const resMyEFFECT = await getMyCostumes(token, "EFFECT");

      setMyHeadItems(resMyHEAD.data.content);
      setMyBodyItems(resMyBODY.data.content);
      setMyEffectItems(resMyEFFECT.data.content);
    };

    getMyItems();
  }, []);

  useEffect(() => {
    setMyHead(props.outfits.head);
    setMyBody(props.outfits.body);
    setMyEffect(props.outfits.effect);
  }, [props.outfits]);

  return (
    <style.Inventory>
      <style.InventoryBar>
        {typeList.map((type, index) => (
          <style.InventoryType
            key={index}
            onClick={() => handleTypeClick(type.name)}
            selected={itemType === type.name}
          >
            <style.InventoryTypeImg src={type.imgSrc} alt={type.name} />
          </style.InventoryType>
        ))}
      </style.InventoryBar>
      {itemType === "HEAD" && (
        <style.InventoryBox>
          {headArr.map((item: any, index: number) => (
            <ItemBox
              item={item}
              key={index}
              selected={myHeadItems.some(
                (myItem: any) =>
                  myItem.costumeId === item.costumeId || item.costumeId === 0
              )}
              checked={myHead.costumeId === item.costumeId}
              onItemClick={handleItemClick}
            />
          ))}
        </style.InventoryBox>
      )}
      {itemType === "BODY" && (
        <style.InventoryBox>
          {bodyArr.map((item: any, index: number) => (
            <ItemBox
              item={item}
              key={index}
              selected={myBodyItems.some(
                (myItem: any) =>
                  myItem.costumeId === item.costumeId || item.costumeId === 0
              )}
              checked={myBody.costumeId === item.costumeId}
              onItemClick={handleItemClick}
            />
          ))}
        </style.InventoryBox>
      )}
      {itemType === "EFFECT" && (
        <style.InventoryBox>
          {effectArr.map((item: any, index: number) => (
            <ItemBox
              item={item}
              key={index}
              selected={myEffectItems.some(
                (myItem: any) =>
                  myItem.costumeId === item.costumeId || item.costumeId === 0
              )}
              checked={myEffect.costumeId === item.costumeId}
              onItemClick={handleItemClick}
            />
          ))}
        </style.InventoryBox>
      )}
    </style.Inventory>
  );
};

export default Inventory;
