import TextBox from "@/components/common/TextBox/TextBox";
import * as style from "./style/GithubBadge";

const BADGE_URL = "![COBBY_BADGE](https://cobby-play.com/baefrica/badge...)";

const GithubBadge = () => {
  
  const copyBadgeText = () => {
    navigator.clipboard.writeText(BADGE_URL);
  }

  return (
    <style.GithubBadgeWrapper>
      <style.BadgeTxt>
        <TextBox size={30} content={"# GITHUB README BADGE"} />
      </style.BadgeTxt>
      <style.CodeCopyBox>
        <style.ReadmeCode>
          <TextBox
            size={20}
            content={BADGE_URL}
          />
        </style.ReadmeCode>
        <style.CopyBtnImg src="/copybutton.png" onClick={copyBadgeText} />
      </style.CodeCopyBox>
    </style.GithubBadgeWrapper>
  );
};
export default GithubBadge;
