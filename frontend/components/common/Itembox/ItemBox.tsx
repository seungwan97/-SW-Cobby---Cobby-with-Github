import * as style from "./style/ItemBox";
import Image from "next/image";

// ItemBox
const ItemBox = (props: any) => {
  const handleItemClick = (gifSrc: String) => {
    console.log(props.item.gifSrc);
  };

  return (
    <style.ImageWrapper
      onClick={() => handleItemClick(props.item.gifSrc)}
    >
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
