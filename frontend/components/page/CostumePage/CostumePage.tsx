// 유저별 코스튬 페이지
import { Fragment, useState, useEffect, useLayoutEffect } from "react";
import * as page from "@/components/layout/PageWrapper/style/PageWrapper";
import BottomNavBar from "@/components/layout/BottomNavBar/BottomNavBar";
import TextBox from "@/components/common/TextBox/TextBox";
import Inventory from "./CostumeComponents/Inventory";
import * as style from "./CostumeComponents/style/CostumePage";
import Cobby from "@/components/common/Cobby/Cobby";
import { getAvatarInfo, patchAvatarInfo } from "@/pages/api/main";
import cookie from "react-cookies";

<<<<<<< HEAD
// CostumePage
const CostumePage = (props: any) => {
  const [outfits, setOutfits]: any = useState({
    head: {},
    body: {},
    effect: {},
  });
=======
interface Props {
  HEAD_ITEMS: any;
  BODY_ITEMS: any;
  EFFECT_ITEMS: any;
  cobbyOutfits: any;
}

// CostumePage
const CostumePage = (props: Props) => {
  const [outfits, setOutfits]: any = useState(props.cobbyOutfits);
>>>>>>> b0bd697a84067e765ab6e03479a065209faf7f34

  const [cobby, setCobby]: any = useState({
    baseCobby: "/Character/Cobby.gif" + "?" + Date.now(),
    head: null,
    body: null,
    effect: null,
  });

<<<<<<< HEAD
  // 나의 코비의 outfits 를 불러오기
  useEffect(() => {
    const getCobbyOutfits = async () => {
      const token = cookie.load("Authorization");

      try {
        const res = await getAvatarInfo(token);
        const cobbyOutfits = res.data.content.outfits;

        setOutfits(cobbyOutfits);
      } catch (error) {
        console.error("My Cobby outfits failed.", error);
      }
    };

    getCobbyOutfits();
  }, []);

  // useEffect(() => {
  //   setCobby({
  //     baseCobby: "/Character/Cobby.gif" + "?" + Date.now(),
  //     head: outfits.head.gifUrl,
  //     body: outfits.body.gifUrl,
  //     effect: outfits.effect.gifUrl,
  //   });
  // }, [outfits]);

  const handleInventoryItem = (itemInfo: any) => {
    console.log("전달받은 itemInfo : ", itemInfo);

=======
  const handleInventoryItem = (itemInfo: any) => {
>>>>>>> b0bd697a84067e765ab6e03479a065209faf7f34
    // 나의 Cobby 의 outfits 업데이트해주기
    setOutfits((prevOutfits: any) => {
      let updatedOutfits = { ...prevOutfits };

      if (itemInfo.category === "HEAD") {
        updatedOutfits.head = itemInfo;

        setCobby((state: any) => {
<<<<<<< HEAD
          // const nextState = state.head
          //   ? { ...state, head: null }
          //   : {
          //       baseCobby: newImgReq("/Character/Cobby.gif"),
          //       head: newImgReq(updatedOutfits.head.gifUrl),
          //       body: state.body ? newImgReq(updatedOutfits.body.gifUrl) : null,
          //       effect: state.effect
          //         ? newImgReq(updatedOutfits.effect.gifUrl)
          //         : null,
          //     };

=======
>>>>>>> b0bd697a84067e765ab6e03479a065209faf7f34
          const nextState = {
            baseCobby: newImgReq("/Character/Cobby.gif"),
            head: newImgReq(updatedOutfits.head.gifUrl),
            body: state.body ? newImgReq(updatedOutfits.body.gifUrl) : null,
            effect: state.effect
              ? newImgReq(updatedOutfits.effect.gifUrl)
              : null,
          };

          return nextState;
        });
      } else if (itemInfo.category === "BODY") {
        updatedOutfits.body = itemInfo;

        setCobby((state: any) => {
<<<<<<< HEAD
          // const nextState = state.body
          //   ? { ...state, body: null }
          //   : {
          //       baseCobby: newImgReq("/Character/Cobby.gif"),
          //       head: state.head ? newImgReq(updatedOutfits.head.gifUrl) : null,
          //       body: newImgReq(updatedOutfits.body.gifUrl),
          //       effect: state.effect
          //         ? newImgReq(updatedOutfits.effect.gifUrl)
          //         : null,
          //     };

=======
>>>>>>> b0bd697a84067e765ab6e03479a065209faf7f34
          const nextState = {
            baseCobby: newImgReq("/Character/Cobby.gif"),
            head: state.head ? newImgReq(updatedOutfits.head.gifUrl) : null,
            body: newImgReq(updatedOutfits.body.gifUrl),
            effect: state.effect
              ? newImgReq(updatedOutfits.effect.gifUrl)
              : null,
          };

          return nextState;
        });
      } else if (itemInfo.category === "EFFECT") {
        updatedOutfits.effect = itemInfo;

        setCobby((state: any) => {
<<<<<<< HEAD
          // const nextState = state.effect
          //   ? { ...state, effect: null }
          //   : {
          //       baseCobby: newImgReq("/Character/Cobby.gif"),
          //       head: state.head ? newImgReq(updatedOutfits.head.gifUrl) : null,
          //       body: state.body ? newImgReq(updatedOutfits.body.gifUrl) : null,
          //       effect: newImgReq(updatedOutfits.effect.gifUrl),
          //     };

=======
>>>>>>> b0bd697a84067e765ab6e03479a065209faf7f34
          const nextState = {
            baseCobby: newImgReq("/Character/Cobby.gif"),
            head: state.head ? newImgReq(updatedOutfits.head.gifUrl) : null,
            body: state.body ? newImgReq(updatedOutfits.body.gifUrl) : null,
            effect: newImgReq(updatedOutfits.effect.gifUrl),
          };

          return nextState;
        });
      }

      // 코비 정보 수정
      const token = cookie.load("Authorization");

      const data = {
        head: updatedOutfits.head.costumeId,
        body: updatedOutfits.body.costumeId,
        effect: updatedOutfits.effect.costumeId,
      };

<<<<<<< HEAD
      patchAvatarInfo(token, data).then((res) => {
        console.log(res.data.content);
      });
=======
      patchAvatarInfo(token, data).then((res) => {});
>>>>>>> b0bd697a84067e765ab6e03479a065209faf7f34

      return updatedOutfits;
    });
  };

  const cacheImages = async (srcArray: any) => {
    const promiseArray = srcArray.map((src: string) => {
      if (src) {
        return new Promise((resolve: any, reject) => {
          const img = new Image();
          img.onload = () => {
            resolve();
          };
          img.onerror = () => {
            reject();
          };
          img.src = src;
        });
      }
    });

    await Promise.all(promiseArray);
  };

  const useCacheImg = (cobby: any) => {
    const [isLoading, setIsLoading] = useState(true);

    useLayoutEffect(() => {
      cacheImages(Object.values(cobby))
        .then(() => {
          setIsLoading(false);
<<<<<<< HEAD
          console.log(cobby);
        })
        .catch((err) => {
          console.log("error!");
=======
        })
        .catch((err) => {
>>>>>>> b0bd697a84067e765ab6e03479a065209faf7f34
          setIsLoading(true);
        });
    }, [cobby]);

    return isLoading;
  };

  const isLoading = useCacheImg(cobby);

  const newImgReq = (src: string) => {
    return src + "?" + Date.now();
  };

  return (
    <Fragment>
      <page.PageWrapper>
        <style.CostumePageTextWrapper>
          <TextBox size={50} content={"COSTUME"} />
        </style.CostumePageTextWrapper>
        <style.CostumedCobby>
          <Cobby outfits={outfits} isLoading={isLoading} cobby={cobby} />
        </style.CostumedCobby>
        <Inventory
          headItemList={props.HEAD_ITEMS}
          bodyItemList={props.BODY_ITEMS}
          effectItemList={props.EFFECT_ITEMS}
          outfits={outfits}
          onItemClick={handleInventoryItem}
        />
      </page.PageWrapper>
      <BottomNavBar />
    </Fragment>
  );
};

export default CostumePage;
