//ItemBox
import * as style from "./style/ItemBox";
import TextBox from "./TextBox";
const ItemBox = () => {
  // 일단 레이아웃만
  return (
    <style.ItemWrapper>
      <style.ItemImage></style.ItemImage>
      {/* <style.ItemLabel size={10}>
        <TextBox size={20} content={"uniform"} />
      </style.ItemLabel> */}
    </style.ItemWrapper>
  );
};

export default ItemBox;
