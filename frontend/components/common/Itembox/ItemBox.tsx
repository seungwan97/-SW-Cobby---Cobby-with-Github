import * as style from "./style/ItemBox";
import Image from "next/image";

// ItemBox
const ItemBox = (props: any) => {
  const handleItemClick = () => {
    props.onItemClick(props.item.gifSrc);
  };

  return (
    <style.ImageWrapper onClick={() => handleItemClick()}>
      <Image
        src={props.item.item}
        alt="item"
        width={80}
        height={65}
      />
    </style.ImageWrapper>
  );
};

export default ItemBox;
