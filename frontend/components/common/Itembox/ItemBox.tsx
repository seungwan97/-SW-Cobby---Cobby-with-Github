import * as style from "./style/ItemBox";
import Image from "next/image";

// ItemBox
const ItemBox = (props: any) => {
  const handleItemClick = () => {
    props.onItemClick(props.item);
  };

  return (
    <style.ImageWrapper
      select={props.selected}
      check={props.checked}
      onClick={() => {
        handleItemClick();
      }}
    >
      <style.ItemImage
        src={props.item.imgUrl}
        alt={props.item.name}
        width={80}
        height={65}
      />
      <style.Filter select={props.selected} />
      <style.LockFilter select={props.selected} />
    </style.ImageWrapper>
  );
};

export default ItemBox;
