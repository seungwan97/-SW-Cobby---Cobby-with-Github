import * as style from "./style/ItemBox";
<<<<<<< HEAD
import Image from "next/image";

// ItemBox
const ItemBox = (props: any) => {
  const handleItemClick = () => {
    console.log(
      "Inventory 컴포넌트로 ",
      props.item,
      " 전달했습니다."
    );
=======
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
>>>>>>> b0bd697a84067e765ab6e03479a065209faf7f34
    props.onItemClick(props.item);
  };

  return (
    <style.ImageWrapper
<<<<<<< HEAD
      selected={props.selected}
      checked={props.checked}
      onClick={() => {
        console.log(
          "ItemBox 컴포넌트에서 아이템을 클릭했습니다."
        );
=======
      select={props.selected}
      getto={props.getto}
      onClick={() => {
>>>>>>> b0bd697a84067e765ab6e03479a065209faf7f34
        handleItemClick();
      }}
    >
      <style.ItemImage
        src={props.item.imgUrl}
        alt={props.item.name}
        width={80}
        height={65}
      />
<<<<<<< HEAD
=======
      {/* {!props.isOpened && props.getto && isNew ? (
        <style.isNew>new</style.isNew>
      ) : null} */}
      <style.Filter select={props.getto} />
      <style.LockFilter select={props.getto} />
>>>>>>> b0bd697a84067e765ab6e03479a065209faf7f34
    </style.ImageWrapper>
  );
};

export default ItemBox;
