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
      checked={props.checked}
      onClick={() => handleItemClick()}
    >
      <style.ItemImage
        src={props.item.imgUrl}
        alt={props.item.name}
        width={80}
        height={65}
      />
    </style.ImageWrapper>
  );
};

export default ItemBox;
