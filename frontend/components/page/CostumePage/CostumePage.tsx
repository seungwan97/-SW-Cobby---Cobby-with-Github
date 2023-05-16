// 유저별 코스튬 페이지
import {
  Fragment,
  useState,
  useEffect,
  useLayoutEffect,
} from "react";
import * as page from "@/components/layout/PageWrapper/style/PageWrapper";
import BottomNavBar from "@/components/layout/BottomNavBar/BottomNavBar";
import TextBox from "@/components/common/TextBox/TextBox";
import Inventory from "./CostumeComponents/Inventory";
import * as style from "./CostumeComponents/style/CostumePage";
import Cobby from "@/components/common/Cobby/Cobby";
import {
  getAvatarInfo,
  patchAvatarInfo,
} from "@/pages/api/main";

// CostumePage
const CostumePage = (props: any) => {
  const [outfits, setOutfits]: any = useState({
    head: {},
    body: {},
    effect: {},
  });

  const [cobby, setCobby]: any = useState({
    baseCobby: "/Character/Cobby.gif" + "?" + Date.now(),
    head: null,
    body: null,
    effect: null,
  });

  // 나의 코비의 outfits 를 불러오기
  useEffect(() => {
    const getCobbyOutfits = async () => {
      const token = "Bearer-eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJmZDJkMDlmNC1lOTA0LTQyZDMtOTQwMy0wMzJkODE0ZDVhNjYiLCJyb2xlIjoiUk9MRV9VU0VSIiwiaWF0IjoxNjg0MjUxOTM0LCJleHAiOjE2ODQyNTU1MzR9.p9miuyHDFwDG3ImN31G17LfapE3Y17ZM2YpNaeq9jG0";

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

    // 나의 Cobby 의 outfits 업데이트해주기
    setOutfits((prevOutfits: any) => {
      let updatedOutfits = { ...prevOutfits };

      if (itemInfo.category === "HEAD") {
        updatedOutfits.head = itemInfo;

        setCobby((state: any) => {
          const nextState = state.head
            ? { ...state, head: null }
            : {
              baseCobby: newImgReq(
                "/Character/Cobby.gif"
              ),
              head: newImgReq(updatedOutfits.head.gifUrl),
              body: state.body
                ? newImgReq(updatedOutfits.body.gifUrl)
                : null,
              effect: state.effect
                ? newImgReq(updatedOutfits.effect.gifUrl)
                : null,
            };
          return nextState;
        });
      } else if (itemInfo.category === "BODY") {
        updatedOutfits.body = itemInfo;

        setCobby((state: any) => {
          const nextState = state.body
            ? { ...state, body: null }
            : {
              baseCobby: newImgReq(
                "/Character/Cobby.gif"
              ),
              head: state.head
                ? newImgReq(updatedOutfits.head.gifUrl)
                : null,
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
          const nextState = state.effect
            ? { ...state, effect: null }
            : {
              baseCobby: newImgReq(
                "/Character/Cobby.gif"
              ),
              head: state.head
                ? newImgReq(updatedOutfits.head.gifUrl)
                : null,
              body: state.body
                ? newImgReq(updatedOutfits.body.gifUrl)
                : null,
              effect: newImgReq(
                updatedOutfits.effect.gifUrl
              ),
            };
          return nextState;
        });
      }

      // 코비 정보 수정
      const userId = "Bearer-eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJmZDJkMDlmNC1lOTA0LTQyZDMtOTQwMy0wMzJkODE0ZDVhNjYiLCJyb2xlIjoiUk9MRV9VU0VSIiwiaWF0IjoxNjg0MjUxOTM0LCJleHAiOjE2ODQyNTU1MzR9.p9miuyHDFwDG3ImN31G17LfapE3Y17ZM2YpNaeq9jG0";
      const data = {
        head: updatedOutfits.head.costumeId,
        body: updatedOutfits.body.costumeId,
        effect: updatedOutfits.effect.costumeId,
      };

      patchAvatarInfo(userId, data).then((res) => {
        console.log(res.data.content);
      });

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
          console.log(cobby);
        })
        .catch((err) => {
          console.log("error!");
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
          <Cobby
            outfits={outfits}
            isLoading={isLoading}
            cobby={cobby}
          />
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
