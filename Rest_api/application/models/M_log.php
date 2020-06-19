
<?php

class M_log extends CI_Model
{
    function getAllLog()
    {
        return  $this->db->get('logbook')->result_array();
    }

    function getIdLog($id_log)
    {
        return $this->db->get_where('logbook', ['id_log' => $id_log]);
    }

    function insertLogbook($judul, $uraian, $id_user)
    {
        return $this->db->insert('logbook', ['judul' => $judul, 'uraian' => $uraian, 'id_user' => $id_user]);
    }

    function updateLogbook($id_log, $judul, $uraian, $id_user)
    {
        $this->db->update('logbook', ['judul' => $judul, 'uraian' => $uraian, 'id_user' => $id_user], ['id_log' => $id_log]);
    }

    function deleteLogbook($id_log)
    {
        $this->db->delete('logbook', ['id_log' => $id_log]);
    }
}
