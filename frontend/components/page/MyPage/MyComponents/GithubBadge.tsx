import TextBox from "@/components/common/TextBox/TextBox";
import * as style from "./style/GithubBadge";

const GithubBadge = () => {
  return (
    <style.GithubBadgeWrapper>
      <style.BadgeTxt>
        <TextBox size={30} content={"# GITHUB README BADGE"} />
      </style.BadgeTxt>
      <style.CodeCopyBox>
        <style.ReadmeCode>
          <TextBox
            size={15}
            content={"https://cobby-play.com/baefrica/badge..."}
          />
        </style.ReadmeCode>
        <style.CopyBtnImg src="/copybutton.png" />
      </style.CodeCopyBox>
    </style.GithubBadgeWrapper>
  );
};
export default GithubBadge;
