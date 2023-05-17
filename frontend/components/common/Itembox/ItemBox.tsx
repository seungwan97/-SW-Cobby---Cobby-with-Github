import * as style from "./style/ItemBox";
import Image from "next/image";

// ItemBox
const ItemBox = (props: any) => {
  const handleItemClick = () => {
    console.log("Inventory 컴포넌트로 ", props.item, " 전달했습니다.");
    props.onItemClick(props.item);
  };

  return (
    <style.ImageWrapper
      select={props.selected}
      check={props.checked}
      onClick={() => {
        console.log("ItemBox 컴포넌트에서 아이템을 클릭했습니다.");
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
