import * as style from "./style/ItemBox";
import Image from "next/image";

// ItemBox
const ItemBox = (props: any) => {
  const handleItemClick = () => {
    props.onItemClick(props.item);
  };

  return (
    <style.ImageWrapper
      selected={props.selected}
      onClick={() => handleItemClick()}
    >
      <Image
        src={props.item.imgUrl}
        alt={props.item.name}
        selected={props.selected}
        width={80}
        height={65}
      />
    </style.ImageWrapper>
  );
};

export default ItemBox;
