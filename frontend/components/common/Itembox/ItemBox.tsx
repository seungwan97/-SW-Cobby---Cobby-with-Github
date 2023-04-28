import * as style from "./style/ItemBox";
import Image from "next/image";

// ItemBox
const ItemBox = (props: any) => {
  // 일단 레이아웃만
  return (
    <style.ImageWrapper>
      <Image src={props.item.item} alt="item" width={80} height={65} />
    </style.ImageWrapper>
  );
};

export default ItemBox;
