import * as style from "./style/ItemBox";
import { patchInventories } from "@/pages/api/main";
import cookie from "react-cookies";

// ItemBox
const ItemBox = (props: any) => {
  const handleItemClick = async () => {
    const costumeId = props.costumeId.filter(
      (item: any) => item !== undefined
    )[0];

    if (props.isOpened === false) {
      const token = cookie.load("Authorization");

      await patchInventories(costumeId, token);
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
