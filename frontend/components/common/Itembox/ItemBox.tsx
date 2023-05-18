import * as style from "./style/ItemBox";
import Image from "next/image";

// ItemBox
const ItemBox = (props: any) => {
  const handleItemClick = () => {
    if (props.isOpened === false) {
      // 여기서 서버로 다시 isOpened 바꿔달라고 요청
    }
    props.onItemClick(props.item);
  };
  // console.log("isDefault", props.isDefault);
  console.log("isOpened", props.isOpened);

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
      {!props.isOpened && props.getto ? <style.isNew>new</style.isNew> : null}
      <style.Filter select={props.getto} />
      <style.LockFilter select={props.getto} />
    </style.ImageWrapper>
  );
};

export default ItemBox;
