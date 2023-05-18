import * as style from "./style/ItemBox";
import { patchInventories } from "@/pages/api/main";
import { useEffect, useState } from "react";
import cookie from "react-cookies";

// ItemBox
const ItemBox = (props: any) => {
  const [isNew, setIsNew] = useState(true);

  useEffect(() => {
    if (props.getto) {
      setIsNew(false);
    }
  });

  const handleItemClick = async () => {
    // const costumeId = props.costumeId.filter(
    //   (item: any) => item !== undefined
    // )[0];

    // if (props.isOpened === false) {
    //   const token = cookie.load("Authorization");

    //   await patchInventories(costumeId, token).then((res) => {
    //     if (res.status === 200) {
    //       setIsNew(false);
    //     }
    //   });
    // }
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
      {/* {!props.isOpened && props.getto && isNew ? (
        <style.isNew>new</style.isNew>
      ) : null} */}
      <style.Filter select={props.getto} />
      <style.LockFilter select={props.getto} />
    </style.ImageWrapper>
  );
};

export default ItemBox;
