package com.example.picserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 
 * </p>
 *
 * @author jesse
 * @since 2021-09-28
 */
@ApiModel(value="SysVodDetail对象", description="")
open class SysVodDetail : Serializable {

    var groupId: Int? = null
    var typeId: Int? = null
    var typeId1: Int? = null
    var typeName: String? = null
    var vodActor: String? = null
    var vodArea: String? = null
    var vodAuthor: String? = null
    var vodBehind: String? = null
    var vodBlurb: String? = null
    var vodClass: String? = null
    var vodColor: String? = null
    var vodContent: String? = null
    var vodCopyright: Int? = null
    var vodDirector: String? = null
    var vodDoubanId: Int? = null
    var vodDoubanScore: String? = null
    var vodDown: Int? = null
    var vodDownFrom: String? = null
    var vodDownNote: String? = null
    var vodDownServer: String? = null
    var vodDownUrl: String? = null
    var vodDuration: String? = null
    var vodEn: String? = null
    var vodHits: Int? = null
    var vodHitsDay: Int? = null
    var vodHitsMonth: Int? = null
    var vodHitsWeek: Int? = null
    @TableId(value = "vod_id", type = IdType.AUTO)
    var vodId: Int? = null
    var vodIsend: Int? = null
    var vodJumpurl: String? = null
    var vodLang: String? = null
    var vodLetter: String? = null
    var vodLevel: Int? = null
    var vodLock: Int? = null
    var vodName: String? = null
    var vodPic: String? = null
    var vodPicScreenshot: String? = null
    var vodPicSlide: String? = null
    var vodPicThumb: String? = null
    var vodPlayFrom: String? = null
    var vodPlayNote: String? = null
    var vodPlayServer: String? = null
    var vodPlayUrl: String? = null
    var vodPlot: Int? = null
    var vodPlotDetail: String? = null
    var vodPlotName: String? = null
    var vodPoints: Int? = null
    var vodPointsDown: Int? = null
    var vodPointsPlay: Int? = null
    var vodPubdate: String? = null
    var vodPwd: String? = null
    var vodPwdDown: String? = null
    var vodPwdDownUrl: String? = null
    var vodPwdPlay: String? = null
    var vodPwdPlayUrl: String? = null
    var vodPwdUrl: String? = null
    var vodRelArt: String? = null
    var vodRelVod: String? = null
    var vodRemarks: String? = null
    var vodReurl: String? = null
    var vodScore: String? = null
    var vodScoreAll: Int? = null
    var vodScoreNum: Int? = null
    var vodSerial: String? = null
    var vodState: String? = null
    var vodStatus: Int? = null
    var vodSub: String? = null
    var vodTag: String? = null
    var vodTime: LocalDateTime? = null
    var vodTimeAdd: Int? = null
    var vodTimeHits: Int? = null
    var vodTimeMake: Int? = null
    var vodTotal: Int? = null
    var vodTpl: String? = null
    var vodTplDown: String? = null
    var vodTplPlay: String? = null
    var vodTrysee: Int? = null
    var vodTv: String? = null
    var vodUp: Int? = null
    var vodVersion: String? = null
    var vodWeekday: String? = null
    var vodWriter: String? = null
    var vodYear: String? = null


    override fun toString(): String {
        return "SysVodDetail{" +
        "groupId=" + groupId +
        ", typeId=" + typeId +
        ", typeId1=" + typeId1 +
        ", typeName=" + typeName +
        ", vodActor=" + vodActor +
        ", vodArea=" + vodArea +
        ", vodAuthor=" + vodAuthor +
        ", vodBehind=" + vodBehind +
        ", vodBlurb=" + vodBlurb +
        ", vodClass=" + vodClass +
        ", vodColor=" + vodColor +
        ", vodContent=" + vodContent +
        ", vodCopyright=" + vodCopyright +
        ", vodDirector=" + vodDirector +
        ", vodDoubanId=" + vodDoubanId +
        ", vodDoubanScore=" + vodDoubanScore +
        ", vodDown=" + vodDown +
        ", vodDownFrom=" + vodDownFrom +
        ", vodDownNote=" + vodDownNote +
        ", vodDownServer=" + vodDownServer +
        ", vodDownUrl=" + vodDownUrl +
        ", vodDuration=" + vodDuration +
        ", vodEn=" + vodEn +
        ", vodHits=" + vodHits +
        ", vodHitsDay=" + vodHitsDay +
        ", vodHitsMonth=" + vodHitsMonth +
        ", vodHitsWeek=" + vodHitsWeek +
        ", vodId=" + vodId +
        ", vodIsend=" + vodIsend +
        ", vodJumpurl=" + vodJumpurl +
        ", vodLang=" + vodLang +
        ", vodLetter=" + vodLetter +
        ", vodLevel=" + vodLevel +
        ", vodLock=" + vodLock +
        ", vodName=" + vodName +
        ", vodPic=" + vodPic +
        ", vodPicScreenshot=" + vodPicScreenshot +
        ", vodPicSlide=" + vodPicSlide +
        ", vodPicThumb=" + vodPicThumb +
        ", vodPlayFrom=" + vodPlayFrom +
        ", vodPlayNote=" + vodPlayNote +
        ", vodPlayServer=" + vodPlayServer +
        ", vodPlayUrl=" + vodPlayUrl +
        ", vodPlot=" + vodPlot +
        ", vodPlotDetail=" + vodPlotDetail +
        ", vodPlotName=" + vodPlotName +
        ", vodPoints=" + vodPoints +
        ", vodPointsDown=" + vodPointsDown +
        ", vodPointsPlay=" + vodPointsPlay +
        ", vodPubdate=" + vodPubdate +
        ", vodPwd=" + vodPwd +
        ", vodPwdDown=" + vodPwdDown +
        ", vodPwdDownUrl=" + vodPwdDownUrl +
        ", vodPwdPlay=" + vodPwdPlay +
        ", vodPwdPlayUrl=" + vodPwdPlayUrl +
        ", vodPwdUrl=" + vodPwdUrl +
        ", vodRelArt=" + vodRelArt +
        ", vodRelVod=" + vodRelVod +
        ", vodRemarks=" + vodRemarks +
        ", vodReurl=" + vodReurl +
        ", vodScore=" + vodScore +
        ", vodScoreAll=" + vodScoreAll +
        ", vodScoreNum=" + vodScoreNum +
        ", vodSerial=" + vodSerial +
        ", vodState=" + vodState +
        ", vodStatus=" + vodStatus +
        ", vodSub=" + vodSub +
        ", vodTag=" + vodTag +
        ", vodTime=" + vodTime +
        ", vodTimeAdd=" + vodTimeAdd +
        ", vodTimeHits=" + vodTimeHits +
        ", vodTimeMake=" + vodTimeMake +
        ", vodTotal=" + vodTotal +
        ", vodTpl=" + vodTpl +
        ", vodTplDown=" + vodTplDown +
        ", vodTplPlay=" + vodTplPlay +
        ", vodTrysee=" + vodTrysee +
        ", vodTv=" + vodTv +
        ", vodUp=" + vodUp +
        ", vodVersion=" + vodVersion +
        ", vodWeekday=" + vodWeekday +
        ", vodWriter=" + vodWriter +
        ", vodYear=" + vodYear +
        "}"
    }
}
