import TextBox from "@/components/common/TextBox/TextBox";
import * as style from "./style/GithubBadge";

const GithubBadge = (props: any) => {
  const copyBadgeText = () => {
    navigator.clipboard.writeText(
      `![COBBY_BADGE](https://cobby-play.com/api/user/badge/${props.nickname})`
    );
    props.setIsCopied(true);
  };

  return (
    <style.GithubBadgeWrapper>
      <style.BadgeTxt>
        <TextBox size={30} content={"# GITHUB README BADGE"} />
      </style.BadgeTxt>
      <style.CodeCopyBox>
        <style.ReadmeCode>
          <style.CustomTextBox size={20}>
            {`![COBBY_BADGE](https://cobby-play.com/api/user/badge/${props.nickname})`}
          </style.CustomTextBox>
        </style.ReadmeCode>
        <style.CopyBtnImg src="/copybutton.png" onClick={copyBadgeText} />
      </style.CodeCopyBox>
    </style.GithubBadgeWrapper>
  );
};
export default GithubBadge;
