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
      getto={props.getto}
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
      <style.Filter select={props.getto} />
      <style.LockFilter select={props.getto} />
    </style.ImageWrapper>
  );
};

export default ItemBox;
