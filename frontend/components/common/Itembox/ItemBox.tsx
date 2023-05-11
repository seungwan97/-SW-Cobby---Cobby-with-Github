import * as style from "./style/ItemBox";
import Image from "next/image";

// ItemBox
const ItemBox = (props: any) => {
  const handleItemClick = () => {
    props.onItemClick(props.item.gifUrl);
  };

  return (
    <style.ImageWrapper onClick={() => handleItemClick()}>
      <Image
        src={props.item.imgUrl}
        alt={props.item.name}
        width={80}
        height={65}
      />
    </style.ImageWrapper>
  );
};

export default ItemBox;
