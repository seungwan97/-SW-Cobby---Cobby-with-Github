import TextBox from "@/components/common/TextBox/TextBox";
import * as style from "./style/GithubBadge";

<<<<<<< HEAD
const BADGE_URL = "![COBBY_BADGE](https://cobby-play.com/baefrica/badge...)sfsdfasfkldsnfknsklnskjvnaklsjvnjkvnkjsnksj";

const GithubBadge = (props: any) => {

  const copyBadgeText = () => {
    navigator.clipboard.writeText(BADGE_URL);
    props.setIsCopied(true);
  }
=======
const GithubBadge = (props: any) => {
  const copyBadgeText = () => {
    navigator.clipboard.writeText(
      `![COBBY_BADGE](https://cobby-play.com/api/user/badge/${props.nickname})`
    );
    props.setIsCopied(true);
  };
>>>>>>> b0bd697a84067e765ab6e03479a065209faf7f34

  return (
    <style.GithubBadgeWrapper>
      <style.BadgeTxt>
        <TextBox size={30} content={"# GITHUB README BADGE"} />
      </style.BadgeTxt>
      <style.CodeCopyBox>
        <style.ReadmeCode>
          <style.CustomTextBox size={20}>
<<<<<<< HEAD
            {BADGE_URL}
=======
            {`![COBBY_BADGE](https://cobby-play.com/api/user/badge/${props.nickname})`}
>>>>>>> b0bd697a84067e765ab6e03479a065209faf7f34
          </style.CustomTextBox>
        </style.ReadmeCode>
        <style.CopyBtnImg src="/copybutton.png" onClick={copyBadgeText} />
      </style.CodeCopyBox>
    </style.GithubBadgeWrapper>
  );
};
export default GithubBadge;
